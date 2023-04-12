package com.ralyon.beerbox.feature.beers

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.ralyon.beerbox.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.ralyon.data.model.Beer

@Composable
fun BeerBottomSheet(beer: Beer) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (image, name, tagline, desc, icon) = createRefs()

        AsyncImage(
            model = beer.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .width(120.dp)
                .height(200.dp)
                .padding(24.dp)
        )
        Text(
            text = beer.name,
            color = Color.White,
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 24.dp, start = 16.dp, end = 24.dp)
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
                .padding(start = 16.dp, end = 24.dp)
        )
        Text(
            text = beer.description,
            color = Color.White,
            modifier = Modifier
                .alpha(0.5f)
                .constrainAs(desc) {
                    top.linkTo(tagline.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 8.dp, start = 16.dp, end = 24.dp, bottom = 24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark_32),
            contentDescription = "Bookmark Icon",
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 32.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0B181D)
@Composable
private fun BeerBottomSheetPreview() {
    BeerBottomSheet(
        beer = Beer(
            name = "Skull Candy",
            tagline = "Pacific Hopped Amber Bitter.",
            description = "The first beer that we brewed on our newly commissioned 5000 litre brewhouse in Fraserburgh 2009. A beer with the malt and body of an English bitter, but the heart and soul of vibrant citrus US hops."
        )
    )
}