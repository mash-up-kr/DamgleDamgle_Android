package com.mashup.damgledamgle.presentation.feature.home.map

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.DamgleTimeCheckBox
import com.mashup.damgledamgle.presentation.feature.home.map.marker.MarkerCustomView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(context: Context) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                locationTrackingMode = LocationTrackingMode.Follow
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

    var markerCustomView by remember {
        mutableStateOf(MarkerCustomView(context))
    }

    settingMarker(false, false, R.drawable.ic_heart, "")

    val cameraPositionState = rememberCameraPositionState()

    Box(Modifier.fillMaxSize()) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings,
            locationSource = LocalLocationSource.current
        ){
            MapEffect{ map ->
                map.locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_my_position)

            }

            Marker(
                state = MarkerState(position = LatLng(37.5459113, 127.0657051)),
                icon = OverlayImage.fromView(markerCustomView),
            )
            Marker(
                state = MarkerState(position = LatLng(37.232400, 127.0657051)),
                icon = OverlayImage.fromView(markerCustomView),
            )
            Marker(
                state = MarkerState(position = LatLng(37.232400, 127.024612)))

        }
        Column(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            DamgleTimeCheckBox()
        }
    }

    AndroidView(modifier = Modifier
        .wrapContentSize()
        .alpha(0f),
        factory = {
            MarkerCustomView(context).apply {
                post {
                    markerCustomView = this
                }
            }
        }
    )

}

fun settingMarker(isRead : Boolean, isDuple : Boolean, resId : Int, cnt : String) {
    MarkerCustomView.cnt = cnt
    MarkerCustomView.isRead = isRead
    MarkerCustomView.isDuple = isDuple
    MarkerCustomView.resId = resId
}
