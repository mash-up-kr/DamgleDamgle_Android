package com.mashup.damgledamgle.presentation.feature.onboarding

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
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

    var isLocationPermissionAllowed by remember { mutableStateOf(checkPermissionSelf(context, Manifest.permission.ACCESS_FINE_LOCATION)) }
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
            if (isGranted) {
                isLocationPermissionAllowed = true
            } else {
                // Permission Denied
            // TODO(minji): 위치권한 거부할 경우 앱 사용 못함
        }
    }

    var isAlarmPermissionAllowed by remember { mutableStateOf<Boolean?>(true) } // TODO(minji): 알람은 권한체크 필요가 없다? 알아보기
    val alarmPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            isAlarmPermissionAllowed = true
        }
    }

    BackPressHandler {
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
            permission = Manifest.permission.ACCESS_FINE_LOCATION
        )

    } else {
        if (isAlarmPermissionAllowed == null) { // 알림 권한은 동의 여부와 관계 없이 다이얼로그 노출 여부만 체크
            PermissionScreen(
                mainText = context.getString(R.string.permission_alarm_maintext),
                subText = context.getString(R.string.permission_alarm_subtext),
                iconResId = R.drawable.ic_permission_alarm,
                permissionLauncher = alarmPermissionLauncher,
                permission = Manifest.permission.ACCESS_NOTIFICATION_POLICY
            )
        } else {
            NickNameScreen { navController.navigate("home_screen") }
        }
    }
}

fun checkPermissionSelf(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(context, permission)== PackageManager.PERMISSION_GRANTED
}
