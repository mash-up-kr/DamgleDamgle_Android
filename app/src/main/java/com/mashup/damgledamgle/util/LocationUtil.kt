package com.mashup.damgledamgle.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import android.util.Log
import androidx.activity.ComponentActivity
import com.naver.maps.geometry.LatLng

object LocationUtil {

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

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            1000,
            50.0f,
            gpsListener)

        return latLng
    }

    fun convertMyLocationToAddress(latLng: LatLng, context: Context) : String {
        val geocoder = Geocoder(context)
        val address: List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5)
        val position = address[0].getAddressLine(0).split(" ")
        val gu = position[2]
        val dong = position[3]

        return "$gu $dong"
    }
}

