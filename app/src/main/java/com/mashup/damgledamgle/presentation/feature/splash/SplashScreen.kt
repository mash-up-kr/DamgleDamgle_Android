package com.mashup.damgledamgle.presentation.feature

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

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
}
