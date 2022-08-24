package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.BackPressInterceptor
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.home.bottomsheet.BottomSheetContent
import com.mashup.damgledamgle.presentation.feature.leave_story.GNB
import com.mashup.damgledamgle.presentation.feature.mypage.model.MyPageTab
import com.mashup.damgledamgle.ui.theme.*
import kotlinx.coroutines.launch

/**
 *  MyPageScreen.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalPagerApi
@Composable
fun MyPageScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val myPageViewModel: MyPageViewModel = hiltViewModel()

    val tabItems = listOf(MyPageTab.MyDamgle, MyPageTab.Setting)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey500)
    ) {
        when (myPageViewModel.uiState.collectAsState(initial = ViewState.Loading).value) {
            is ViewState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Orange500
                )
            }

            is ViewState.Success -> {
                val profile = myPageViewModel.userProfileState.collectAsState().value as ViewState.Success
                val myDamgleList = myPageViewModel.myDamgleListState.collectAsState().value as ViewState.Success

                val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
                val coroutineScope = rememberCoroutineScope()

                BottomSheetScaffold(
                    sheetBackgroundColor = Color.Gray,
                    sheetShape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp
                    ),
                    sheetContent = {
                        BottomSheetContent(navController, bottomSheetScaffoldState)
                    },
                    sheetPeekHeight = 0.dp,
                    scaffoldState = bottomSheetScaffoldState,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Grey500),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GNB(
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

                        MyProfile(profile.data.nickName)

                        MyPageTabBar(
                            tabItems = tabItems,
                            pagerState = pagerState,
                        )

                        HorizontalPager(
                            count = tabItems.size,
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            if (page == MyPageTab.MyDamgle.index) {
                                TabMyDamglePage(navController, myDamgleList.data) {
                                    coroutineScope.launch {
                                        bottomSheetScaffoldState.bottomSheetState.expand()
                                    }
                                }
                            } else {
                                TabSettingPage(navController, profile.data.notification)
                            }
                        }
                    }
                }

                BackPressInterceptor(context = LocalContext.current) {
                    if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyProfile(userName: String?) {
    Image(
        painter = painterResource(R.drawable.ic_mypage_profile),
        contentDescription = "my profile image",
        modifier = Modifier
            .padding(top = 24.dp)
            .size(56.dp)
    )

    Text(
        text = userName ?: "",
        modifier = Modifier.padding(top = 8.dp),
        style = pretendardTextStyle.bodyMedium13
    )
}
