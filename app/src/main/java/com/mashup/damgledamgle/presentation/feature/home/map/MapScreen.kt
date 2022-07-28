package com.mashup.damgledamgle.presentation.feature.home.map

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.DamgleTimeCheckBox
import com.mashup.damgledamgle.presentation.feature.home.HomeViewModel
import com.mashup.damgledamgle.presentation.feature.home.MakerInfo
import com.mashup.damgledamgle.presentation.feature.home.map.marker.makeMarkerCustomBitmap
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage

val homeViewModel = HomeViewModel()
@Composable
fun MapScreen(cameraPositionState: CameraPositionState) {
    val mContext = LocalContext.current

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                locationTrackingMode = LocationTrackingMode.Follow,
                minZoom = 14.8,
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
                val isMine = makerInfo.isMine

                makeMarkerCustomBitmap(mContext, icons, isMine, isRead, makerInfo.size)
                    ?.let { OverlayImage.fromBitmap(it) }
                    ?.let {
                        Marker(
                            state = MarkerState(position = LatLng(latitude, longitude)),
                            icon = it,
                        )
                    }
            }

        }
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            val result = homeViewModel.getCalendarLastDay()
            if(result.contains("D"))
                DamgleTimeCheckBox(result, false)
            else {
                homeViewModel.startTimer()
                val time by homeViewModel.time.observeAsState()
                time?.let { DamgleTimeCheckBox(it, true) }
            }
        }

    }
}
