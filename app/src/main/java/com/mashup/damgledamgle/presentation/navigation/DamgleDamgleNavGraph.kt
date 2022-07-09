package com.mashup.damgledamgle.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.damgledamgle.presentation.feature.home.HomeScreen
import com.mashup.damgledamgle.presentation.feature.mypage.MypageScreen
import com.mashup.damgledamgle.presentation.feature.splash.SplashScreen
import com.mashup.damgledamgle.presentation.feature.splash.SplashViewModel

@Composable
fun DamgleDamgleNavGraph(navController: NavHostController, context : Context) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, context = context)
        }
        composable(route = Screen.Mypage.route) {
            MypageScreen(navController = navController)
        }
    }
}
