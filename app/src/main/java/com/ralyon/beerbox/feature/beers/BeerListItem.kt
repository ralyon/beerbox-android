package com.ralyon.beerbox.feature.beers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.ralyon.data.model.Beer

@Composable
fun BeerListItem(beer: Beer) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 24.dp, start = 8.dp, end = 32.dp)
    ) {
        val (image, name, tagline, desc, button) = createRefs()

        AsyncImage(
            model = beer.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .constrainAs(image) { start.linkTo(parent.start) }
                .width(80.dp)
                .height(120.dp)
        )
        Text(
            text = beer.name,
            color = Color.White,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(image.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = beer.tagline,
            color = Color.White,
            modifier = Modifier
                .alpha(0.5f)
                .constrainAs(tagline) {
                    top.linkTo(name.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = beer.description,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .alpha(0.5f)
                .constrainAs(desc) {
                    start.linkTo(image.end)
                    bottom.linkTo(button.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = "MORE INFO",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(top = 4.dp)
                .constrainAs(button) {
                    start.linkTo(image.end)
                    bottom.linkTo(parent.bottom)
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = MaterialTheme.colors.primary),
                ) { /* TODO */ }
        )
    }
}

@Preview
@Composable
private fun BeerListItemPreview() {
    BeerListItem(
        beer = Beer(
            name = "Skull Candy",
            tagline = "Pacific Hopped Amber Bitter.",
            description = "The first beer that we brewed on our newly commissioned 5000 litre brewhouse in Fraserburgh 2009. A beer with the malt and body of an English bitter, but the heart and soul of vibrant citrus US hops."
        )
    )
}