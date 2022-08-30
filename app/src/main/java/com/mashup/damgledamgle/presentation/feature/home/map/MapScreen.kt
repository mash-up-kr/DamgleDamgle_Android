package com.mashup.damgledamgle.presentation.feature.home.map

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.common.dialog.NetworkErrorDialog
import com.mashup.damgledamgle.presentation.feature.home.*
import com.mashup.damgledamgle.presentation.feature.home.damgle.DamglePaintExplainDialog
import com.mashup.damgledamgle.presentation.feature.home.damgle.DamgleTimeCheckBox
import com.mashup.damgledamgle.presentation.feature.home.map.marker.makeCustomMarkerView
import com.mashup.damgledamgle.presentation.feature.home.model.Bound
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.util.LocationUtil
import com.mashup.damgledamgle.util.TimeUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage

@Composable
fun MapScreen(
    navController: NavHostController,
    cameraPositionState: CameraPositionState
) {
    val mContext = LocalContext.current
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                minZoom = 10.0,
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isZoomControlEnabled = false,
                isCompassEnabled = false
            )
        )
    }

    MapContent(
        navController,
        cameraPositionState = cameraPositionState,
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        mContext
    )
}


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContent(
    navController : NavHostController,
    cameraPositionState: CameraPositionState,
    mapProperties: MapProperties,
    mapUiSettings: MapUiSettings,
    mContext: Context
) {
    val mapViewModel : MapViewModel = hiltViewModel()
    val homeViewModel : HomeViewModel = hiltViewModel()
    val openNetworkDialog = remember { mutableStateOf(false) }
    val openPaintExplainDialog = remember { mutableStateOf(false) }
    StateDamglePaintExplain(openPaintExplainDialog)
    val showLoading = mapViewModel.showLoading.observeAsState()
    
    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ){
            LocationUtil.getMyLocation(mContext)?.let { MarkerState(position = it) }?.let {
                Marker(
                    state = it,
                    zIndex = -1,
                    icon = OverlayImage.fromResource(R.drawable.ic_my_location_picker)
                )
            }

            MapEffect { map ->
                val top = map.contentBounds.northLatitude
                val bottom = map.contentBounds.southLatitude
                val left = map.contentBounds.westLongitude
                val right = map.contentBounds.eastLongitude

                mapViewModel.currentBound = Bound(top,bottom,left,right)
                mapViewModel.getStoryFeedList(
                    top = top,
                    bottom = bottom,
                    left = left,
                    right = right
                )
            }
            when(mapViewModel.storyFeedState.collectAsState(initial = ViewState.Loading).value) {
                is ViewState.Success -> {
                    val list =  mapViewModel.storyFeedState.collectAsState().value as ViewState.Success
                    list.data.forEach {
                        val latitude = it.position.latitude
                        val longitude = it.position.longitude
                        val bound = it.bound

                        Marker(
                            state = MarkerState(position = LatLng(latitude, longitude)),
                            icon = OverlayImage.fromView(makeCustomMarkerView(it, mContext)),
                            onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set("bound", bound)
                                navController.navigate((Screen.AllDamgleList.route))
                                true
                            }
                        )
                    }
                }
                is ViewState.Error -> {
                    openNetworkDialog.value = true
                    if (openNetworkDialog.value) {
                        NetworkErrorDialog(
                            openNetworkDialog = openNetworkDialog,
                            onButtonClick = { openNetworkDialog.value = false }
                        )
                    }
                }
                else -> {}
            }
            if(showLoading.value == true) {
                MapEffect { map ->
                    val top = map.contentBounds.northLatitude
                    val bottom = map.contentBounds.southLatitude
                    val left = map.contentBounds.westLongitude
                    val right = map.contentBounds.eastLongitude
                    mapViewModel.currentBound = Bound(top, bottom, left, right)
                }
            }
        }
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            CheckDamgleTime(mapViewModel = mapViewModel,
                openPaintExplainDialog = openPaintExplainDialog
            )
        }
        Column(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 120.dp, end = 20.dp)
        ) {
            FloatingActionButton(
                fabIcon = R.drawable.ic_refresh,
                description = "refresh_btn",
                modifier = Modifier.size(48.dp, 48.dp),
                onClick = {
                    mapViewModel.showLoading.value = true
                    val updateLocation = LocationUtil.getMyLocation(mContext)
                    mapViewModel.homeRefreshBtnEvent(homeViewModel, updateLocation)
                }
            )
            FloatingActionButton(
                fabIcon = R.drawable.ic_location_point,
                description = "location_btn",
                modifier = Modifier
                    .paddingFromBaseline(10.dp)
                    .size(48.dp, 48.dp),
                onClick = {
                    LocationUtil.getMyLocation(mContext)?.let { latLng ->
                            cameraPositionState.move(CameraUpdate.scrollTo(latLng))
                    }
                }
            )
        }
        if(showLoading.value == true) {
            LoadingLottie()
        }
    }
}

@Composable
fun CheckDamgleTime(mapViewModel: MapViewModel, openPaintExplainDialog: MutableState<Boolean>) {
    val result = TimeUtil.getCalendarLastDay()
    if(result.contains("D")) {
        DamgleTimeCheckBox(result, false) { openPaintExplainDialog.value = true }
    } else {
        mapViewModel.startTimer()
        val time by mapViewModel.time.observeAsState()
        val oneHourCheck by mapViewModel.oneHourCheck.observeAsState()
        time?.let { oneHourCheck?.let { it1 -> DamgleTimeCheckBox(it, it1) {
            openPaintExplainDialog.value = true } } }
    }
}

@Composable
fun StateDamglePaintExplain(openPaintExplainDialog : MutableState<Boolean>){
    if (openPaintExplainDialog.value) {
        DamglePaintExplainDialog(
            openDamglePainDialog = openPaintExplainDialog
        ) {
            openPaintExplainDialog.value = false
        }
    }
}
