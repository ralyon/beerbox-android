package com.ralyon.beerbox.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ralyon.beerbox.R
import com.ralyon.beerbox.ui.theme.PrimaryTextColor

@Preview
@Composable
fun AppTitle() {
    val (beer, box) = stringResource(R.string.app_name).split(' ')

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = beer,
            color = PrimaryTextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = box,
            color = PrimaryTextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}