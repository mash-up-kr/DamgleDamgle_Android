package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.mashup.damgledamgle.ui.theme.Grey500

/**
 *  PermissionScreen.kt
 *
 *  Created by Minji Jeong on 2022/07/06
 *  Copyright © 2022 GwanakMT All rights reserved.
 */

@Composable
fun PermissionScreen(
    mainText: String,
    subText: String,
    iconResId: Int,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    permission: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey500)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "Damgle Permission Icon",
            modifier = Modifier.padding(top = 88.dp)
        )

        Text(
            text = mainText,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = subText,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
    }

    LocalLifecycleOwner.current.lifecycleScope.launchWhenResumed {
        permissionLauncher.launch(permission)
    }
}
