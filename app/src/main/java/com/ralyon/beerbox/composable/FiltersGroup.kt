package com.ralyon.beerbox.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ralyon.beerbox.theme.BeerBoxTheme

@Composable
fun FiltersGroup(
    modifier: Modifier = Modifier,
    filters: List<String>,
    selectedFilter: String? = null,
    onSelectedChanged: (String?) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        items(filters) { filter ->
            val isSelected = selectedFilter == filter
            FilterChip(
                name = filter,
                isSelected = isSelected,
                onSelectionChanged = {
                    val selection = it.takeIf { !isSelected }
                    onSelectedChanged(selection)
                }
            )
        }
    }
}

@Preview
@Composable
private fun FiltersGroupPreview() {
    BeerBoxTheme {
        FiltersGroup(
            filters = listOf("Blonde", "Lager", "Pils"),
            selectedFilter = "Lager",
            onSelectedChanged = {}
        )
    }
}