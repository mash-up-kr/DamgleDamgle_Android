package com.mashup.damgledamgle

import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mashup.damgledamgle.presentation.feature.home.map.ProvideLocationSource
import com.mashup.damgledamgle.presentation.navigation.DamgleDamgleNavGraph
import com.mashup.damgledamgle.ui.theme.DamgleDamgleTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val locationSource: FusedLocationSource by lazy {
        FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideLocationSource(locationSource = locationSource) {
                getMyLocation()
                DamgleDamgleTheme {
                    navController = rememberNavController()
                    DamgleDamgleNavGraph(navController = navController,this)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if(requestCode == LOCATION_PERMISSION_REQUEST_CODE)
                myLocation = getMyLocation()
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        var myLocation : LatLng? = null
    }

    private fun getMyLocation(): LatLng? {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        return currentLocation?.latitude?.let { LatLng(it, currentLocation.longitude) }
    }
}
