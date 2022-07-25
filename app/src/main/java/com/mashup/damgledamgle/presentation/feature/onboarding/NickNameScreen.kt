package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.*

/**
 *  NickNameScreen.kt
 *
 *  Created by Minji Jeong on 2022/07/06
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun NickNameScreen(
    finishMakeNickName: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val viewModel: OnboardingViewModel = hiltViewModel()

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

            Text(
                text = context.getString(
                    R.string.nickname_placeholder_count,
                    viewModel.nickName.collectAsState().value.nth
                ),
                style = pretendardTextStyle.bodyMedium24,
                color = Orange500,
                modifier = Modifier.padding(top = 56.dp, start = 24.dp)
            )

            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                NickNameBox(viewModel.nickName.collectAsState().value.adjective) {
                    viewModel.refreshNickName(viewModel.nickName.value.copy(
                        adjective = ""
                    ))
                }

                NickNameBox(viewModel.nickName.collectAsState().value.noun) {
                    viewModel.refreshNickName(viewModel.nickName.value.copy(
                        noun = ""
                    ))
                }
            }
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

@Composable
fun NickNameBox(
    nickName: String,
    onClickRefresh: (() -> Unit)? = null
) {
    Box(
        Modifier
            .width(IntrinsicSize.Max)
            .padding(end = 16.dp)
    ) {
        Text(
            text = nickName,
            style = pretendardTextStyle.bodyMedium24,
            color = Gray1000,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 4.dp, end = 32.dp, bottom = 8.dp)
                .wrapContentSize()
        )

        Divider(
            color = Gray1000,
            modifier = Modifier.align(Alignment.BottomStart),
            thickness = 2.dp
        )

        Image(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 4.dp)
                .size(24.dp)
                .clickable { onClickRefresh?.invoke() }
        )
    }
}

@Preview
@Composable
fun PreviewNickNameBox() {
    NickNameBox("닉네임")
}
