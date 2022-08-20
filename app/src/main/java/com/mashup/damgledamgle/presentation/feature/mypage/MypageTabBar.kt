package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.mashup.damgledamgle.presentation.feature.mypage.model.MyPageTab
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.White
import kotlinx.coroutines.launch

/**
 *  MyPageTabBar.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@ExperimentalPagerApi
@Composable
fun MyPageTabBar(
    tabItems: List<MyPageTab>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    val startPadding: Dp by animateDpAsState(if (pagerState.currentPage == MyPageTab.MyDamgle.ordinal) 0.dp else 90.dp)

    Box(
        modifier = Modifier.padding(top = 56.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = startPadding)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = Black
                )
                .width(90.dp)
                .height(40.dp),
        )
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            modifier = Modifier.width(180.dp),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .width(0.dp)
                        .height(0.dp)
                )
            },
            divider = {},
        ) {
            tabItems.forEachIndexed { index, tab ->
                Tab(
                    title = tab.title,
                    isSelected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }
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

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewTabBar() {
    val pagerState = rememberPagerState()

    MyPageTabBar(
        tabItems = listOf(),
        pagerState = pagerState
    )
}
