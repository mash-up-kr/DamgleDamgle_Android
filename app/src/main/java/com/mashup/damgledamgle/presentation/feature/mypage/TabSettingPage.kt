package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.Gray600

/**
 *  TabSettingPage.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun TabSettingPage(
    notification: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight(),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.background_horizontal_orange_gradient),
                contentDescription = "Orange Gradient Background",
            )

            PushSettingItem(notification)
        }

        Text(
            text = "서비스 그만 사용하기",
            modifier = Modifier
                .padding(vertical = 32.dp)
                .clickable { },
            style = TextStyle(
                fontSize = 13.sp,
                color = Gray600,
            ),
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewTabSettingPage() {
    TabSettingPage(true)
}
