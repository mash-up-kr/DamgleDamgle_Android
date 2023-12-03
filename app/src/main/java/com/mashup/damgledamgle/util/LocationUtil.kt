package com.mashup.damgledamgle.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.location.LocationListener
import android.location.LocationRequest
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import com.naver.maps.geometry.LatLng

object LocationUtil {

    fun getMyLocation(context : Context) : LatLng? {
        val locationManager = context.getSystemService(ComponentActivity.LOCATION_SERVICE) as LocationManager
        var latLng : LatLng? = null
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "위치 권한에 동의해야 앱 사용이 가능합니다.", Toast.LENGTH_SHORT).show()
            (context as? Activity)?.finish()
        }
        else {
            val currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ?:
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            latLng = currentLocation?.latitude?.let { LatLng(it, currentLocation.longitude) }
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
        }
        return latLng
    }

    fun getLocation(context: Context, loc: (location: Location) -> Unit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "위치 권한에 동의해야 앱 사용이 가능합니다.", Toast.LENGTH_SHORT).show()
            (context as? Activity)?.finish()
        } else {

            val mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            val priority = LocationRequest.QUALITY_BALANCED_POWER_ACCURACY
            val cancellationTokenSource = CancellationTokenSource()
            mFusedLocationProviderClient.lastLocation.addOnSuccessListener {

            }
            mFusedLocationProviderClient.getCurrentLocation(priority, cancellationTokenSource.token)
                .addOnSuccessListener { locationResult ->
                    Log.d("Result", locationResult.toString())
                    locationResult?.let { loc(it) }
                }
                .addOnFailureListener {
                    Log.d("location result ", it.message.toString())
                }
                .addOnCompleteListener {
                    it.isComplete
                }
        }
    }

    fun convertMyLocationToAddress(latLng: LatLng, context: Context) : String {
        val geocoder = Geocoder(context)
        val address: List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5)
        val position = address.getOrNull(0)?.getAddressLine(0)?.split(" ")
        val gu = position?.getOrNull(2)
        val dong = position?.getOrNull(3)

        return "$gu $dong"
    }
}

