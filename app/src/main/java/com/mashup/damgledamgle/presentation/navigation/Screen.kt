package com.mashup.damgledamgle.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Mypage : Screen("mypage_screen")
    object LeaveStory : Screen("leave_story_screen")
}
