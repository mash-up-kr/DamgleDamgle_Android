package com.mashup.damgledamgle.presentation.feature.home.map

import androidx.compose.runtime.Composable
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.rememberMarkerState

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun Marker() {
    com.naver.maps.map.compose.Marker(
        state = rememberMarkerState(
            position = LatLng(37.5666102, 126.9783881)
        ),
    )
}
