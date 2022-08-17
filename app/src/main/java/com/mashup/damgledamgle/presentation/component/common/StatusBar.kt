package com.mashup.damgledamgle.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 *  StatusBar.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun StatusBar(color: Color, darkIcon: Boolean) {
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = color,
        darkIcons = darkIcon,
    )
}
