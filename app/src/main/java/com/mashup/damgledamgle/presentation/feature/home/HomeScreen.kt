package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.naver.maps.map.compose.ExperimentalNaverMapApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalNaverMapApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    Scaffold {
        BottomSheetScaffold(
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
}


