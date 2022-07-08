package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.BackPressInterceptor

/**
 *  OnboardingScreen.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current

    BackPressInterceptor(context)

    Text("onboarding")
}
