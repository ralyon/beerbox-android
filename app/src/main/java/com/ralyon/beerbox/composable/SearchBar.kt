package com.ralyon.beerbox.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ralyon.beerbox.R
import com.ralyon.beerbox.ui.theme.BeerBoxTheme
import com.ralyon.beerbox.ui.theme.PrimaryTextColor
import com.ralyon.beerbox.ui.theme.SecondaryTextColor

@Composable
fun SearchBar(onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange.invoke(it)
        },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Bar") },
        placeholder = { Text(stringResource(R.string.search_bar_placeholder)) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = PrimaryTextColor,
            leadingIconColor = SecondaryTextColor,
            placeholderColor = SecondaryTextColor,
            backgroundColor = Color(0xFF1A262D)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    BeerBoxTheme {
        SearchBar(onValueChange = {})
    }
}