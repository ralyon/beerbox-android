package com.ralyon.composable

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ralyon.theme.BeerBoxTheme
import com.ralyon.theme.PrimaryTextColor
import com.ralyon.theme.SecondaryTextColor

@Composable
fun SearchBar(onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Bar") },
        placeholder = { Text(stringResource(R.string.search_bar_placeholder)) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = PrimaryTextColor,
            leadingIconColor = SecondaryTextColor,
            placeholderColor = SecondaryTextColor,
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    BeerBoxTheme {
        SearchBar(onValueChange = {})
    }
}