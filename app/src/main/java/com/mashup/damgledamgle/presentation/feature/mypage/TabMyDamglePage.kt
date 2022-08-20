package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.model.DamgleModel
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.ui.theme.Gray400
import com.mashup.damgledamgle.ui.theme.Gray800
import com.mashup.damgledamgle.ui.theme.Grey500
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle

/**
 *  TabMyDamglePage.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun TabMyDamglePage(
    navController: NavHostController?,
    myDamgleList: List<DamgleModel>
) {
    val context = LocalContext.current

    if (myDamgleList.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .height(218.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_empty_gradient),
                    contentDescription = "",
                    modifier = Modifier
                        .size(210.dp)
                        .align(Alignment.TopCenter)
                )

                Text(
                    text = context.getString(R.string.mypage_mydamgle_empty),
                    style = pretendardTextStyle.bodyMedium16,
                    color = Gray800,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

            Text(
                text = context.getString(R.string.mypage_mydamgle_empty_button),
                style = pretendardTextStyle.bodySemibold16,
                modifier = Modifier
                    .clickable {
                        navController?.navigate(Screen.Home.route) {
                            popUpTo(0)
                        }
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gray400)
                    .padding(horizontal = 48.dp, vertical = 20.dp)
            )
        }
    } else {
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_vertical_orange_gradient),
                contentDescription = "Orange Gradient Background",
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(
                        myDamgleList
                    ) { index, item ->
                        MyDamgleItem()
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background_mydamgle_gray_gradient),
                        contentDescription = "To hide Orange gradient when my damgle item count is small ",
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2000.dp)
                            .padding(20.dp)
                            .background(color = Grey500)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTabEmptyDamglePage() {
    TabMyDamglePage(null, emptyList())
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTabListDamglePage() {
    TabMyDamglePage(
        null, listOf(
            DamgleModel(
                id = "",
                userNo = "",
                nickName = "",
                x = 0.0,
                y = 0.0,
                content = "",
                reactions = listOf(),
                createdAt = 0,
                updatedAt = 0
            )
        )
    )
}
