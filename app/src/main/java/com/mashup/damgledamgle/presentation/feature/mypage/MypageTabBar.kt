package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.mypage.model.TabPage
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.Grey500
import com.mashup.damgledamgle.ui.theme.White

/**
 *  MyPageTabBar.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun MyPageTabBar(
    tabPage: TabPage,
    onTabSelected: (tabPage: TabPage) -> Unit
) {
    val context = LocalContext.current

    TabRow(
        selectedTabIndex = tabPage.ordinal,
        backgroundColor = Grey500,
        modifier = Modifier
            .width(186.dp)
            .padding(top = 48.dp),
        indicator = {},
        divider = {},
    ) {
        Tab(
            title = context.getString(R.string.mypage_tab_mydamgle),
            isSelected = tabPage.ordinal == TabPage.MyDamgle.ordinal,
            onClick = { onTabSelected(TabPage.MyDamgle) }
        )

        Tab(
            title = context.getString(R.string.mypage_tab_setting),
            isSelected = tabPage.ordinal == TabPage.Setting.ordinal,
            onClick = { onTabSelected(TabPage.Setting) }
        )
    }
}

@Composable
fun Tab(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Text(
        text = title,
        modifier = Modifier
            .background(
                color = if (isSelected) Black else Grey500,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .height(40.dp)
            .wrapContentHeight(),
        textAlign = Center,
        style = if (isSelected) {
            TextStyle(color = White)
        } else {
            TextStyle(color = Black)
        },
    )
}
