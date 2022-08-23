package com.mashup.damgledamgle.presentation.feature.onboarding

import android.Manifest
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.*

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
    val openSettingPermissionDialogState = remember { mutableStateOf(false) }

    var isLocationPermissionAllowed by remember { mutableStateOf(checkPermissionSelf(context, Manifest.permission.ACCESS_FINE_LOCATION)) }
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            isLocationPermissionAllowed = true
        } else {
            openSettingPermissionDialogState.value = true
        }
    }

    BackPressInterceptor(context) {
        if (System.currentTimeMillis() - backPressWaitTime >= 1500) { // 1.5초 안에 뒤로가기 두 번 눌러야 앱 종료.
            backPressWaitTime = System.currentTimeMillis()
            Toast.makeText(context, context.getString(R.string.common_toast_backpress), Toast.LENGTH_SHORT).show()
        } else {
            (context as? Activity)?.finish()
        }
    }

    if (!isLocationPermissionAllowed) {
        PermissionScreen(
            mainText = context.getString(R.string.permission_location_maintext),
            subText = context.getString(R.string.permission_location_subtext),
            iconResId = R.drawable.ic_permission_location,
            permissionLauncher = locationPermissionLauncher,
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            openSettingPermissionDialogState = openSettingPermissionDialogState
        )
    } else {
        // 현재 안드로이드는 Notification 퍼미션을 받지 않아서 회원가입 할 때는 무조건 true
        NickNameScreen(navController, true)
    }

    if (openSettingPermissionDialogState.value) {
        SettingPermissionDialog(
            openSettingPermissionDialogState = openSettingPermissionDialogState,
            onMoveToSettingButton = { moveToPermissionSettingPage(context) },
            onKillAppButton = { (context as? Activity)?.finish() }
        )
    }
}
