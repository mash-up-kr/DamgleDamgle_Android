package com.mashup.damgledamgle.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.AllDamgleListScreen
import com.mashup.damgledamgle.presentation.feature.home.HomeScreen
import com.mashup.damgledamgle.presentation.feature.leave_story.LeaveStoryScreen
import com.mashup.damgledamgle.presentation.feature.mydamgle.MyDamgleScreen
import com.mashup.damgledamgle.presentation.feature.mypage.MyPageScreen
import com.mashup.damgledamgle.presentation.feature.onboarding.OnboardingScreen
import com.mashup.damgledamgle.presentation.feature.splash.SplashScreen
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.Grey500

@ExperimentalPagerApi
@Composable
fun DamgleDamgleNavGraph(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            systemUiController.setSystemBarsColor(color = Grey500)
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            systemUiController.setSystemBarsColor(color = Black)
            HomeScreen(navController = navController)
        }
        composable(route = Screen.MyPage.route) {
            MyPageScreen(navController = navController)
        }
        composable(
            route = "${Screen.LeaveStory.route}/{content}",
            arguments = listOf(
                navArgument("content") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            systemUiController.setSystemBarsColor(color = Grey500)
            LeaveStoryScreen(navController = navController, entry.arguments?.getString("content") ?: "")
        }
        composable(route = Screen.AllDamgleList.route) {
            systemUiController.setSystemBarsColor(color = Grey500)
            AllDamgleListScreen(navController = navController)
        }
        composable(route = Screen.MyDamgle.route) {
            systemUiController.setSystemBarsColor(color = Grey500)
            MyDamgleScreen(navController)
        }
    }
}
