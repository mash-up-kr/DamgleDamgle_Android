package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.mashup.damgledamgle.R
import com.naver.maps.map.compose.LocationTrackingMode

//TODO - onclick 파라미터로 받기
@Composable
fun FloatingActionButton(fabIcon : Int, description : String, modifier: Modifier) {
    FloatingActionButton(
        modifier = modifier,
        backgroundColor = colorResource(id = R.color.damgle_default_black),
        onClick = { LocationTrackingMode.Follow }) {
        Icon(
            painterResource(id = fabIcon),
            description,
            tint = Color.White
        )
    }
}
