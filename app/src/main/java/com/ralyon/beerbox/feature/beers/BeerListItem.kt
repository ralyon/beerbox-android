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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.ralyon.beerbox.R
import com.ralyon.beerbox.theme.PrimaryTextColor
import com.ralyon.beerbox.theme.SecondaryTextColor
import com.ralyon.data.model.Beer

@Composable
fun BeerListItem(beer: Beer, onMoreInfoClicked: (Beer) -> Unit, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
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
            color = PrimaryTextColor,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(image.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = beer.tagline,
            color = SecondaryTextColor,
            modifier = Modifier
                .constrainAs(tagline) {
                    top.linkTo(name.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = beer.description,
            color = SecondaryTextColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(desc) {
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    top.linkTo(tagline.bottom)
                    bottom.linkTo(button.top)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = stringResource(R.string.beer_list_item_more_info_button).uppercase(),
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
                ) { onMoreInfoClicked.invoke(beer) }
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
        ),
        onMoreInfoClicked = {}
    )
}