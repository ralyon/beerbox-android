package com.ralyon.beerbox.feature.beers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
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
            Title()
            BeerList(beers)
        }
    }
}

@Composable
fun Title() {
    Text(
        text = "Beer Box",
        color = Color.White,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun BeerList(beers: LazyPagingItems<Beer>) {
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(items = beers, key = { it.id }) { beer ->
            beer?.let { BeerListItem(it) }
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

@Composable
fun BeerListItem(beer: Beer) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = beer.name)
    }
}