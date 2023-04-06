package com.ralyon.beerbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ralyon.beerbox.feature.beers.BeerListScreen
import com.ralyon.beerbox.ui.theme.BeerBoxTheme
import com.ralyon.beerbox.ui.theme.DarkBackground
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
                    color = DarkBackground,
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