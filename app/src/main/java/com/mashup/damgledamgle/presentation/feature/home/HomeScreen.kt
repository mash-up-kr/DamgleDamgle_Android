package com.mashup.damgledamgle.presentation.feature.home

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.MainActivity
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.map.MapScreen
import com.mashup.damgledamgle.presentation.feature.toolbar.MainToolBar
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.rememberCameraPositionState


@OptIn(ExperimentalMaterialApi::class, ExperimentalNaverMapApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val mContext = LocalContext.current
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val cameraPositionState = rememberCameraPositionState()
    val currentLocation = MainActivity.myLocation
    currentLocation?.let { latLng ->
        cameraPositionState.move(CameraUpdate.scrollTo(latLng)) }

    BottomSheetScaffold(
        topBar = {
            MainToolBar(
                title = currentLocation?.let { convertMyLocationToAddress(it, mContext) }
            ) { navController.navigate(Screen.Mypage.route) }
        },
        sheetBackgroundColor = Color.Gray,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetContent = {
            BottomSheetContent(bottomSheetScaffoldState)
        },
        sheetPeekHeight = 100.dp,
        scaffoldState = bottomSheetScaffoldState,
        floatingActionButton = {
            Spacer(modifier = Modifier.height(240.dp))
            FloatingActionButton(
                fabIcon = R.drawable.ic_refresh,
                description = "refresh_btn",
                modifier = Modifier.size(48.dp, 48.dp),
                onClick = {
                }
            )
            FloatingActionButton(
                fabIcon = R.drawable.ic_location_point,
                description = "location_btn",
                modifier = Modifier
                    .paddingFromBaseline(56.dp)
                    .size(48.dp, 48.dp),
                onClick = {
                    currentLocation?.let { latLng ->
                        cameraPositionState.move(CameraUpdate.scrollTo(latLng))
                    }
                }
            )
        }
    ) {
        MapScreen(cameraPositionState)
    }
}

fun convertMyLocationToAddress(latLng: LatLng, context: Context) : String {
    val geocoder = Geocoder(context)
    val address : List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude,5)
    val position = address[0].getAddressLine(0).split(" ")
    val gu = position[2]
    val dong = position[3]

    return "$gu $dong"
}
