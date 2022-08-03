package com.mashup.damgledamgle.presentation.feature.home.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng

object LocationManager {

    @SuppressLint("MissingPermission")
    fun getMyLocation(context : Context) : LatLng? {

        val locationManager = context.getSystemService(ComponentActivity.LOCATION_SERVICE) as LocationManager
        val currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ?:
        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        var latLng : LatLng? = currentLocation?.latitude?.let { LatLng(it, currentLocation.longitude) }
        val gpsListener =  object : LocationListener {
            override fun onLocationChanged(location : Location) {
                val latitude = location.latitude
                val longitude = location.longitude
                Log.d("Test", "GPS Location changed, Latitude: $latitude" +
                    ", Longitude: $longitude")
                latLng = LatLng(latitude,longitude)
            }
            override fun onProviderDisabled(provider: String) {}
            override fun onProviderEnabled(provider: String) {}
        }

        if(currentLocation == null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,
                50.0f,
                gpsListener)
        }


        return latLng
    }

}

