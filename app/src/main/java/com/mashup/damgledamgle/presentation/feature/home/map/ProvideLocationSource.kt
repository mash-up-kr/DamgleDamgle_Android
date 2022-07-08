package com.mashup.damgledamgle.presentation.feature.home.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.naver.maps.map.LocationSource

val LocalLocationSource = staticCompositionLocalOf<LocationSource> {
    error("CompositionLocal LocalLocationSource not present")
}

@Composable
fun ProvideLocationSource(locationSource: LocationSource, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLocationSource provides locationSource, content = content)
}