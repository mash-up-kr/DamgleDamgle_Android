package com.mashup.damgledamgle.presentation.feature.home.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.activity.ComponentActivity
import com.naver.maps.geometry.LatLng

object LocationManager {
    
    @SuppressLint("MissingPermission")
    fun getMyLocation(context: Context): LatLng? {
        val locationManager = context.getSystemService(ComponentActivity.LOCATION_SERVICE) as LocationManager
        val currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ?:
        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        return currentLocation?.latitude?.let { LatLng(it, currentLocation.longitude) }
    }

}