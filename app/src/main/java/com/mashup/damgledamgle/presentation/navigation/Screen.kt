package com.mashup.damgledamgle.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Onboarding: Screen("onboarding_screen")
    object Home : Screen("home_screen")
    object MyPage : Screen("mypage_screen")
    object LeaveStory : Screen("leave_story_screen")
    object AllDamgleList : Screen("all_damgle_list_screen")
}
