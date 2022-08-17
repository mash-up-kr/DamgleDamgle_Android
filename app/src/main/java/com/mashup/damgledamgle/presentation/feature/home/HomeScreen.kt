package com.mashup.damgledamgle.presentation.feature.home

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.BackPressInterceptor
import com.mashup.damgledamgle.presentation.feature.home.bottomsheet.BottomSheetContent
import com.mashup.damgledamgle.presentation.feature.toolbar.MainToolBar
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.naver.maps.map.compose.ExperimentalNaverMapApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalNaverMapApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    var backPressWaitTime by remember { mutableStateOf(0L) }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BackPressInterceptor(context) {
        if (System.currentTimeMillis() - backPressWaitTime >= 1500) { // 1.5초 안에 뒤로가기 두 번 눌러야 앱 종료.
            backPressWaitTime = System.currentTimeMillis()
            Toast.makeText(context, context.getString(R.string.common_toast_backpress), Toast.LENGTH_SHORT).show()
        } else {
            (context as? Activity)?.finish()
        }
    }

    Scaffold {
        BottomSheetScaffold(
            sheetBackgroundColor = Color.Gray,
            sheetShape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp
            ),
            sheetContent = {
                BottomSheetContent(navController, bottomSheetScaffoldState)
            },
            sheetPeekHeight = 100.dp,
            scaffoldState = bottomSheetScaffoldState,
        ) {
            MainToolBar(title = "Position")
            // TODO(minji): 임시 버튼. 추후 삭제 필요.
            Button(onClick = { navController.navigate(Screen.MyPage.route) }) {
                Text(text = "MyPage")
            }
            MapScreen()

        }
    }
}


