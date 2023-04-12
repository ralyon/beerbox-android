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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ralyon.beerbox.composable.AppTitle
import com.ralyon.data.model.Beer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BeerListScreen(viewModel: BeerListViewModel = viewModel()) {

    val beers = viewModel.getBeers().collectAsLazyPagingItems()
    var selectedBeer by remember { mutableStateOf(Beer()) }

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
fun BeerList(beers: LazyPagingItems<Beer>, onBeerSelected: (Beer) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = beers, key = { it.id }) { beer ->
            beer?.let {
                BeerListItem(
                    beer = it,
                    onMoreInfoClicked = { selectedBeer -> onBeerSelected.invoke(selectedBeer) }
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
fun BeerListLoadingItem() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BeerListErrorItem(message: String?) {
    Text(
        text = message ?: "An error occurred loading the beers",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}