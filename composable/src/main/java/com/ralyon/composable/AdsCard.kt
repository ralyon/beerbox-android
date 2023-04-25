package com.ralyon.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ralyon.theme.BeerBoxTheme

@Composable
fun AdsCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    color = MaterialTheme.colors.background,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    color = MaterialTheme.colors.background
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_gift),
                contentDescription = "Gift Image"
            )
        }
    }
}

@Preview
@Composable
private fun AdsCardPreview() {
    BeerBoxTheme {
        AdsCard(title = "Weekend Offers", description = "Free shipping on orders over 60€")
    }
}