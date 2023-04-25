package com.ralyon.beerbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ralyon.feature_beers.ui.BeerListScreen
import com.ralyon.theme.BeerBoxTheme
import com.ralyon.theme.DarkBackgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            // Set the system bars color
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = isSystemInDarkTheme()
            DisposableEffect(systemUiController, useDarkIcons) {
                systemUiController.setSystemBarsColor(
                    color = DarkBackgroundColor,
                    darkIcons = useDarkIcons
                )
                onDispose {}
            }

            BeerBoxTheme {
                BeerListScreen()
            }
        }
    }
}