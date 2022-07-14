package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R

/**
 *  TabMyDamglePage.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun TabMyDamglePage() {
    Box(
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_vertical_orange_gradient),
            contentDescription = "Orange Gradient Background",
            modifier = Modifier.fillMaxWidth()
        )

        // TODO(minji): RowColumn으로 교체 및 데이터 모델 전달하도록 수정
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.padding(top = 20.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            repeat(10) {
                MyDamgleItem()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTabDamglePage() {
    TabMyDamglePage()
}
