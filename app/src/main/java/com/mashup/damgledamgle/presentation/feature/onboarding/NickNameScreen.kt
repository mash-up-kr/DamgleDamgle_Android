package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.*

/**
 *  NickNameScreen.kt
 *
 *  Created by Minji Jeong on 2022/07/06
 *  Copyright © 2022 GwanakMT All rights reserved.
 */

@Composable
fun NickNameScreen(
    finishMakeNickName: (() -> Unit)? = null
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey500),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = context.getString(R.string.nickname_maintext),
                style = pretendardTextStyle.title1Bold32,
                color = Gray1000,
                modifier = Modifier.padding(top = 94.dp, bottom = 8.dp, start = 20.dp),
            )

            Text(
                text = context.getString(R.string.nickname_subtext),
                style = pretendardTextStyle.bodyMedium13,
                color = Gray900,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Column(modifier = Modifier.weight(1f, false)) {
            Image(
                painter = painterResource(id = R.drawable.damgle_nickname_background),
                contentDescription = "Damgle NickName Background",
                modifier = Modifier
                    .padding(start = 38.dp, bottom = 34.dp)
                    .height(104.dp)
            )

            Text(
                textAlign = TextAlign.Center,
                text = context.getString(R.string.nickname_button_start),
                modifier = Modifier
                    .background(Orange500)
                    .fillMaxWidth()
                    .height(64.dp)
                    .clickable { finishMakeNickName?.invoke() }
                    .padding(vertical = 20.dp),
                style = pretendardTextStyle.bodyMedium18,
                color = White
            )
        }
    }
}
