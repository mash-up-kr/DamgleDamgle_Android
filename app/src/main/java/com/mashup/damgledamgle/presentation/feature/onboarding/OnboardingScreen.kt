package com.mashup.damgledamgle.presentation.feature.onboarding

import android.app.Activity
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.BackPressHandler

/**
 *  OnboardingScreen.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current
    var backPressWaitTime by remember { mutableStateOf(0L) }

    BackPressHandler {
        if (System.currentTimeMillis() - backPressWaitTime >= 1500) { // 1.5초 안에 뒤로가기 두 번 눌러야 앱 종료.
            backPressWaitTime = System.currentTimeMillis()
            Toast.makeText(context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            (context as? Activity)?.finish()
        }
    }

    Text("onboarding")
}
