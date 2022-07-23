package com.mashup.damgledamgle.presentation.feature.home.map

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.DamgleTimeCheckBox
import com.mashup.damgledamgle.presentation.feature.home.HomeViewModel
import com.mashup.damgledamgle.presentation.feature.home.MakerInfo
import com.mashup.damgledamgle.presentation.feature.home.map.marker.MarkerCustomView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MapConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

@Composable
fun MapScreen(cameraPositionState: CameraPositionState) {
    val mContext = LocalContext.current
    val homeViewModel = HomeViewModel()

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                locationTrackingMode = LocationTrackingMode.Follow,
//                minZoom = 16.0,
//                maxZoom = 12.0,
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
        cameraPositionState = cameraPositionState,
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        homeViewModel.getMakerList(),
        mContext
    )
}


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContent(
    cameraPositionState: CameraPositionState,
    mapProperties: MapProperties,
    mapUiSettings: MapUiSettings,
    list: ArrayList<MakerInfo>,
    mContext: Context
) {
    var markerCustomView by remember {
        mutableStateOf(MarkerCustomView(mContext))
    }

    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings,
            locationSource = LocalLocationSource.current
        ){
            MapEffect{ map ->
                map.locationOverlay.bearing = 0f
                map.locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_my_location_picker)
            }

            list.forEach { makerInfo ->
                val icons = makerInfo.resId
                val latitude = makerInfo.latitude
                val longitude = makerInfo.longitude
                val isRead = makerInfo.isRead

                Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    icon = OverlayImage.fromView(markerCustomView),
                )
            }

        }
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            DamgleTimeCheckBox("")
        }
    }
}
