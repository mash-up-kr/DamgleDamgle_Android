package com.mashup.damgledamgle.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.naver.maps.geometry.LatLng

object LocationUtil {

    fun getMyLocation(context: Context, onLocationChange: (LatLng?) -> Unit) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "위치 권한에 동의해야 앱 사용이 가능합니다.", Toast.LENGTH_SHORT).show()
            (context as? Activity)?.finish()
        } else {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            val locationRequest = LocationRequest.create().apply {
                priority = PRIORITY_HIGH_ACCURACY
            }

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult.locations.firstOrNull()?.let { location ->
                        onLocationChange(LatLng(location.latitude, location.longitude))
                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    fun convertMyLocationToAddress(latLng: LatLng, context: Context): String {
        val geocoder = Geocoder(context)
        val address: List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5)
        val position = address.getOrNull(0)?.getAddressLine(0)?.split(" ")
        val gu = position?.getOrNull(2)
        val dong = position?.getOrNull(3)

        return "$gu $dong"
    }
}

