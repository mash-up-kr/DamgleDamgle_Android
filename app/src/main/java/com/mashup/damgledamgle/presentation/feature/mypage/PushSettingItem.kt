package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.DisabledInteractionSource
import com.mashup.damgledamgle.ui.theme.*

/**
 *  PushSettingItem.kt
 *
 *  Created by Minji Jeong on 2022/07/09
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun PushSettingItem(
    notification: Boolean,
    patchNotificationState: ()->Unit
) {
    val pushAllowState = remember { mutableStateOf(notification) }

    Box(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth()
    ) {
        Image( // 푸시 설정 컴포넌트 배경 이미지
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.background_setting_gray_gradient),
            contentDescription = "Setting Background",
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .matchParentSize()
                .padding(vertical = 24.dp, horizontal = 48.dp),
        ) {
            Column {
                Text(
                    text = "PUSH 알림 동의",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Gray900
                    )
                )

                Text(
                    text = "내 주변 이야기가 등장하면\n바로 알려드려요!",
                    style = TextStyle(fontSize = 13.sp, color = Gray800),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // 푸시 알림 동의 스위치
            Switch(
                checked = pushAllowState.value,
                interactionSource = remember { DisabledInteractionSource() },
                onCheckedChange = {
                    pushAllowState.value = it
                    patchNotificationState()
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Orange500,
                    checkedTrackColor = Gray400,
                    uncheckedThumbColor = Gray700,
                    uncheckedTrackColor = Gray600,
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Grey500)
            )
        }
    }
}
