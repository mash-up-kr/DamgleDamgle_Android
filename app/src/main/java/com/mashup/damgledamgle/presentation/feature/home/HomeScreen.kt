package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.BackPressInterceptor
import com.mashup.damgledamgle.presentation.feature.home.bottomsheet.BottomSheetContent
import com.mashup.damgledamgle.presentation.feature.home.damgle.DamglePaintDialog
import com.mashup.damgledamgle.presentation.feature.home.map.MapScreen
import com.mashup.damgledamgle.presentation.feature.toolbar.MainToolBar
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.util.LocationUtil
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel : HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    BackPressInterceptor(context)

    val openDamglePaintDialog = remember { mutableStateOf(false) }
    if (homeViewModel.checkEntryAfterDamgleDay()) {
        openDamglePaintDialog.value = true
    }
    if (openDamglePaintDialog.value) {
        DamglePaintDialog(
            date = homeViewModel.getLastEntryDamgleDay(),
            openDamglePainDialog = openDamglePaintDialog
        ) {
            openDamglePaintDialog.value = false
            homeViewModel.setLastEntryDamgleDay()
            navController.navigate("damgle_clear_time_screen")
        }
    }

    var locationTitle by remember {
        mutableStateOf("")
    }
    val currentLocation = LocationUtil.getMyLocation(context)
    homeViewModel.getNaverGeocode(
        "${currentLocation?.longitude},${currentLocation?.latitude}"
    )
    val current = homeViewModel.locationTitle.observeAsState()
    if (current.value != null) {
        locationTitle = current.value!!.ifEmpty {
            currentLocation?.let { LocationUtil.convertMyLocationToAddress(it, context) }.toString()
        }
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    Scaffold {
        BottomSheetScaffold(
            topBar = {
                MainToolBar(
                    title = locationTitle
                ) { navController.navigate(Screen.MyPage.route) }
            },
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
            MapScreen(navController)
        }

        val coroutineScope = rememberCoroutineScope()
        BackPressInterceptor(context = LocalContext.current) {
            if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }
    }
}

