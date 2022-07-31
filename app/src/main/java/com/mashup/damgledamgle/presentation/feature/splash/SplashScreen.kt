package com.mashup.damgledamgle.presentation.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.StatusBar
import com.mashup.damgledamgle.ui.theme.Grey500
import com.mashup.damgledamgle.presentation.navigation.Screen

@Composable
fun SplashScreen(navController: NavHostController) {
    val viewModel: SplashViewModel = hiltViewModel()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    val lottieAnimatable = rememberLottieAnimatable()
    val currentFrame = composition?.getFrameForProgress(lottieAnimatable.value)

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }

    LaunchedEffect(currentFrame) {
        if (currentFrame != null && currentFrame >= 89.90) {
            if (viewModel.isUserRegistered.value == true) {
                navController.navigate(Screen.Home.route) { popUpTo(0) }
            } else {
                navController.navigate(Screen.Onboarding.route){ popUpTo(0) }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Grey500)
    ) {
        StatusBar(color = Grey500, darkIcon = true)
        LottieAnimation(
            composition = composition,
            progress = lottieAnimatable.progress,
        )
    }
}
