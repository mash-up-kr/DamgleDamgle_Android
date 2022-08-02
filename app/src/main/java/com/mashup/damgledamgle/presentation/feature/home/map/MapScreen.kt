package com.mashup.damgledamgle.presentation.feature.home.map

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.map.marker.makeMarkerCustomBitmap
import com.mashup.damgledamgle.presentation.feature.home.timer.DamgleTimeCheckBox
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage

@Composable
fun MapScreen(cameraPositionState: CameraPositionState) {
    val mContext = LocalContext.current
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
               // minZoom = 14.8,
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
        mContext
    )
}


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContent(
    cameraPositionState: CameraPositionState,
    mapProperties: MapProperties,
    mapUiSettings: MapUiSettings,
    mContext: Context
) {
    val mapViewModel : MapViewModel = hiltViewModel()

    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ){

            LocationManager.getMyLocation(mContext)?.let { MarkerState(position = it) }?.let {
                Marker(
                    state = it,
                    icon = OverlayImage.fromResource(R.drawable.ic_my_location_picker))
            }

            mapViewModel.getMakerList().forEach { markerInfo ->
                val icons = markerInfo.resId
                val latitude = markerInfo.latitude
                val longitude = markerInfo.longitude
                val isRead = markerInfo.isRead
                val isMine = markerInfo.isMine

                Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    icon = OverlayImage.fromBitmap(makeMarkerCustomBitmap(mContext, icons, isMine, isRead, markerInfo.size)),
                )
            }

        }
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            CheckDamgleTime(mapViewModel = mapViewModel)
        }
    }
}

@Composable
fun CheckDamgleTime(mapViewModel: MapViewModel) {
    val result = mapViewModel.getCalendarLastDay()
    if(result.contains("D"))
        DamgleTimeCheckBox(result, false)
    else {
        mapViewModel.startTimer()
        val time by mapViewModel.time.observeAsState()
        val oneHourCheck by mapViewModel.oneHourCheck.observeAsState()
        time?.let { oneHourCheck?.let { it1 -> DamgleTimeCheckBox(it, it1) } }
    }
}
