package com.mashup.damgledamgle.presentation.feature.home.map.marker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.rememberMarkerState

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun Marker() {
    com.naver.maps.map.compose.Marker(
        state = rememberMarkerState(
            position = LatLng(37.5666102, 126.9783881)
        )
    )
}

@Preview
@Composable
fun CustomMarker() {
    Box{
        Image(
            painter = painterResource(id = R.drawable.ic_balloon_comm),
            contentDescription = ""
        )
        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(25.dp)
        )
    }
}

@Preview
@Composable
fun MarkerWithReadCheck() {
    Box(){
        Image(
            painter = painterResource(id = R.drawable.ic_balloon_me),
            contentDescription = ""
        )
        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size(25.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_notify),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
        )
    }
}

@Preview
@Composable
fun MarkerWithNotify() {
    Box(){
        Box(
            modifier = Modifier.padding(6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_balloon_me),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(25.dp)
            )
        }
        Box(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_count_background),
                contentDescription = "", 
                modifier = Modifier.size(22.dp) 
            )
            Text(text = "99+",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 8.sp)
        }
    }
}
