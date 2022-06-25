package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.Gray400

/**
 *  TabMyDanglePage.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun TabMyDanglePage() {
    Text(
        text = "내 담글",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .background(Gray400)
            .fillMaxSize()
    )
}
