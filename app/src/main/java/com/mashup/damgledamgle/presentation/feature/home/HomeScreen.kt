package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.presentation.feature.toolbar.MainToolBar
import com.naver.maps.map.compose.ExperimentalNaverMapApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalNaverMapApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        topBar = {
            MainToolBar(
                title = "서울시 마포구"
            ) { navController.navigate(Screen.Mypage.route) }
        },
        sheetBackgroundColor = Color.Gray,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetContent = {
            BottomSheetContent(bottomSheetScaffoldState)
        },
        sheetPeekHeight = 100.dp,
        scaffoldState = bottomSheetScaffoldState,
    ) {
        MapScreen()
    }
}


