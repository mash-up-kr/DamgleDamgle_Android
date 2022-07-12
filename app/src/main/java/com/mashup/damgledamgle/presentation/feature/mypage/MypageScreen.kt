package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.mypage.model.TabPage
import com.mashup.damgledamgle.ui.theme.Grey500

/**
 *  MyPageScreen.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun MyPageScreen(navController: NavHostController) {
    var currentPage by remember { mutableStateOf(TabPage.MyDamgle) }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Grey500),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "my profile image",
                modifier = Modifier
                    .padding(20.dp)
                    .size(24.dp)
                    .align(Alignment.End)
                    .clickable { navController.popBackStack()},
            )

            MyProfile()

            MyPageTabBar(
                tabPage = currentPage,
                onTabSelected = { currentPage = it }
            )

            when (currentPage) {
                TabPage.MyDamgle -> TabMyDanglePage()
                TabPage.Setting -> TabSettingPage()
            }
        }
    }
}

@Composable
fun MyProfile() {
    // TODO(minji): 임시 이미지, 텍스트. 추후 교체 필요.
    Image(
        painter = painterResource(R.drawable.ic_myprofile),
        contentDescription = "my profile image",
        modifier = Modifier
            .padding(top = 24.dp)
            .size(56.dp)
    )

    Text(
        text = "1번째 말많은 코알라",
        modifier = Modifier.padding(top = 8.dp)
    )
}
