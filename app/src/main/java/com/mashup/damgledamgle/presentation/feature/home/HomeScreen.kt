package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.BackPressInterceptor
import com.mashup.damgledamgle.presentation.common.DisabledInteractionSource
import com.mashup.damgledamgle.presentation.feature.home.bottomsheet.BottomSheetContent
import com.mashup.damgledamgle.presentation.feature.home.map.MapScreen
import com.mashup.damgledamgle.presentation.feature.toolbar.MainToolBar
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.ui.theme.LottieBackGround
import com.mashup.damgledamgle.util.LocationUtil
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterialApi::class, ExperimentalNaverMapApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    BackPressInterceptor(context)

    var locationTitle by remember {
        mutableStateOf("")
    }
    val cameraPositionState = rememberCameraPositionState()
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

    currentLocation?.let { CameraUpdate.scrollTo(it) }
        ?.let { cameraPositionState.move(it) }

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
            floatingActionButton = {
                Spacer(modifier = Modifier.height(240.dp))
                FloatingActionButton(
                    fabIcon = R.drawable.ic_refresh,
                    description = "refresh_btn",
                    modifier = Modifier.size(48.dp, 48.dp),
                    onClick = {
                        val updateLocation = LocationUtil.getMyLocation(context)
                        homeViewModel.homeRefreshBtnEvent(updateLocation)
                    }
                )
                FloatingActionButton(
                    fabIcon = R.drawable.ic_location_point,
                    description = "location_btn",
                    modifier = Modifier
                        .paddingFromBaseline(56.dp)
                        .size(48.dp, 48.dp),
                    onClick = {
                        currentLocation?.let { latLng ->
                            cameraPositionState.move(CameraUpdate.scrollTo(latLng))
                        }
                    }
                )
            }
        ) {
            MapScreen(cameraPositionState)
        }
        val showLoading = homeViewModel.showLoading.observeAsState()
        if (showLoading.value == true) {
            LoadingLottie()
            Button(onClick = { navController.navigate(Screen.AllDamgleList.route) }) {
                Text(text = "AllDamgleList")
            }
        }
    }
}

@Composable
fun LoadingLottie() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.refresh_lottie)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 2000),
            initialProgress = 0f
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(LottieBackGround.copy(0.5F))
            .clickable(interactionSource = DisabledInteractionSource(), indication = null) { },
    ) {
        LottieAnimation(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit,
            composition = composition,
            progress = lottieAnimatable.progress
        )
    }

}
