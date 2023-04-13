package com.ralyon.beerbox.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ralyon.beerbox.ui.theme.BackgroundColor
import com.ralyon.beerbox.ui.theme.BeerBoxTheme
import com.ralyon.beerbox.ui.theme.SecondaryTextColor

@Composable
fun FilterChip(
    name: String,
    isSelected: Boolean,
    onSelectionChanged: (String) -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
        modifier = Modifier.toggleable(
            value = isSelected,
            onValueChange = { onSelectionChanged(name) })
    ) {
        Text(
            text = name,
            color = if (isSelected) BackgroundColor else SecondaryTextColor,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
        )
    }
}

@Preview
@Composable
private fun FilterChipPreview() {
    BeerBoxTheme {
        FilterChip("Lager", true) {}
    }
}