package com.mashup.damgledamgle.presentation.feature.home.map

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import com.naver.maps.map.*
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    navController: NavHostController
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

    val cameraPositionState = rememberCameraPositionState()
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
    navController: NavHostController,
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

    if(mapViewModel.movingBound == null) {
        LocationUtil.getMyLocation(mContext)?.let { CameraUpdate.scrollTo(it) }
        ?.let { cameraPositionState.move(it) }
    }
    if(!cameraPositionState.isMoving) {
        mapViewModel.movingBound = LatLng(
            cameraPositionState.position.target.latitude,
            cameraPositionState.position.target.longitude
        )
    } else {
        if(cameraPositionState.contentBounds != null && mapViewModel.movingBound != null) {
            Log.d("refresh", "moving bound")
            mapViewModel.getStoryFeedList(
                top = cameraPositionState.contentBounds!!.northLatitude,
                bottom = cameraPositionState.contentBounds!!.southLatitude,
                left = cameraPositionState.contentBounds!!.westLongitude,
                right = cameraPositionState.contentBounds!!.eastLongitude
            )
        }
    }

    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ){
            LocationUtil.getLocation(mContext) {
                mapViewModel.updateMyLocation(LatLng(it.latitude, it.longitude))
            }
            mapViewModel.myLocation.value?.let {
                Marker(
                    state = MarkerState(position = it),
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
        val coroutineScope = rememberCoroutineScope()
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
                    LocationUtil.getLocation(mContext) {
                        mapViewModel.homeRefreshBtnEvent(homeViewModel, LatLng(it.latitude, it.longitude))
                    }
                }
            )
            FloatingActionButton(
                fabIcon = R.drawable.ic_location_point,
                description = "location_btn",
                modifier = Modifier
                    .paddingFromBaseline(10.dp)
                    .size(48.dp, 48.dp),
                onClick = {
                    val animation = CameraAnimation.Fly
                    LocationUtil.getLocation(mContext) {
                        val position = CameraPosition(LatLng(it.latitude, it.longitude),16.0)
                        coroutineScope.launch {
                            position.let { CameraUpdate.toCameraPosition(it) }.let {
                                cameraPositionState.animate(
                                    it,
                                    animation = animation,
                                    durationMs = 1000
                                )
                            }
                        }
                    }
                }
            )
        }
        if(showLoading.value == true) {
            LoadingLottie()
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CheckDamgleTime(mapViewModel: MapViewModel, openPaintExplainDialog: MutableState<Boolean>) {
    val result = TimeUtil.getCalendarLastDay()
    if(result.contains("D")) {
        DamgleTimeCheckBox(result, false) { openPaintExplainDialog.value = true }
    } else {
        if(!mapViewModel.timerStatus.value) mapViewModel.startTimer()
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
