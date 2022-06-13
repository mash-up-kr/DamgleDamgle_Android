package com.mashup.damgledamgle.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.damgledamgle.presentation.feature.SplashScreen

@Composable
fun DamgleDamgleNavGraph(navController: NavHostController) {
    NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
    }
}
