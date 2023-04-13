package com.ralyon.beerbox.feature.beers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ralyon.beerbox.R
import com.ralyon.beerbox.composable.AdsCard
import com.ralyon.beerbox.composable.AppTitle
import com.ralyon.beerbox.composable.FiltersGroup
import com.ralyon.beerbox.composable.SearchBar
import com.ralyon.beerbox.theme.PrimaryTextColor
import com.ralyon.data.model.Beer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BeerListScreen(viewModel: BeerListViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    var selectedBeer by remember { mutableStateOf(Beer()) }
    val beers = viewModel.getBeers(
        beerName = uiState.searchedName,
        malt = uiState.selectedMalt
    ).collectAsLazyPagingItems()

    val skipHalfExpanded by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppTitle()
            SearchBar(
                onValueChange = { viewModel.setSearchedName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            uiState.adInfo?.let {
                AdsCard(
                    title = it.title,
                    description = it.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            FiltersGroup(
                filters = stringArrayResource(R.array.beer_list_malt_names).toList(),
                selectedFilter = uiState.selectedMalt,
                onSelectedChanged = { name ->
                    viewModel.setSelectedMalt(name)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            ModalBottomSheetLayout(
                sheetState = sheetState,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetBackgroundColor = MaterialTheme.colors.background,
                sheetContent = { BeerBottomSheet(selectedBeer) }) {
                BeerList(
                    beers = beers,
                    onBeerSelected = {
                        selectedBeer = it
                        scope.launch { sheetState.show() }
                    }
                )
            }
        }
    }
}

@Composable
private fun BeerList(beers: LazyPagingItems<Beer>, onBeerSelected: (Beer) -> Unit) {
    LazyColumn {
        items(items = beers, key = { it.id }) { beer ->
            beer?.let {
                BeerListItem(
                    beer = it,
                    onMoreInfoClicked = { selectedBeer -> onBeerSelected.invoke(selectedBeer) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 24.dp, start = 8.dp, end = 32.dp)
                )
            }
            Divider(
                color = Color.White,
                startIndent = 24.dp,
                modifier = Modifier.alpha(0.1f)
            )
        }

        when (val state = beers.loadState.refresh) { // First load
            is LoadState.Error -> {
                item { BeerListErrorItem(message = state.error.localizedMessage) }
            }
            is LoadState.Loading -> {
                item { BeerListLoadingItem() }
            }
            else -> Unit
        }

        when (val state = beers.loadState.append) { // Pagination
            is LoadState.Error -> {
                item { BeerListErrorItem(message = state.error.localizedMessage) }
            }
            is LoadState.Loading -> {
                item { BeerListLoadingItem() }
            }
            else -> Unit
        }
    }
}

@Composable
private fun BeerListLoadingItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun BeerListErrorItem(message: String?) {
    Text(
        text = message ?: stringResource(R.string.beer_list_generic_loading_error),
        color = PrimaryTextColor,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}