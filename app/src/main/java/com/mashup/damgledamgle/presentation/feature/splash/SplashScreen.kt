package com.mashup.damgledamgle.presentation.feature.splash

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    Scaffold(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary,
            content =
            {
                Text(text = "Splash")
            }
    )
    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.navigate("home_screen")
    }
}
