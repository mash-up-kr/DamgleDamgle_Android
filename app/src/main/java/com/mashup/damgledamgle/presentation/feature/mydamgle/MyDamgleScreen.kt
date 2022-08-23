package com.mashup.damgledamgle.presentation.feature.mydamgle

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.leave_story.GNB
import com.mashup.damgledamgle.ui.theme.*

/**
 *  MyDamgleScreen.kt
 *
 *  Created by Minji Jeong on 2022/08/23
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun MyDamgleScreen(navController: NavHostController) {
    androidx.compose.material.Surface(
        color = Grey500,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            GNB(
                centerContent = {
                    Text(
                        text = "내 담글 확인하기",
                        style = pretendardTextStyle.bodyMedium16,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 28.dp),
                        textAlign = TextAlign.Center
                    )
                },
                rightContent = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier
                            .width(24.dp)
                            .offset(x = (-16).dp)
                            .padding(top = 28.dp)
                            .clickable { navController.popBackStack() }
                    )
                }
            )
        }

    }
}