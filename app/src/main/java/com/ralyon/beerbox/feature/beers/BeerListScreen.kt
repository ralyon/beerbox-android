package com.ralyon.beerbox.feature.beers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun BeerListScreen(viewModel: BeerListViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val beers = viewModel.getBeers().collectAsLazyPagingItems()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppTitle()
            BeerList(beers)
        }
    }
}

@Composable
fun BeerList(beers: LazyPagingItems<Beer>) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = beers, key = { it.id }) { beer ->
            beer?.let { BeerListItem(it) }
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