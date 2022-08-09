package com.mashup.damgledamgle.presentation.feature.home

import android.app.Application
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.*
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.home.GetNaverGeocodeUseCase
import com.mashup.damgledamgle.presentation.feature.home.map.LocationManager
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNaverGeocodeUseCase: GetNaverGeocodeUseCase,
    application: Application) : AndroidViewModel(application) {

    val context
        get() = getApplication<Application>()
    var showLoading = MutableLiveData(false)

    private val _geocode = MutableLiveData("")
    val geocode: LiveData<String> = _geocode
    val currentLocation = LocationManager.getMyLocation(context)

    init {
        getNaverGeocode("${currentLocation?.longitude},${currentLocation?.latitude}")
    }

    private fun getNaverGeocode(coords : String) {
        viewModelScope.launch {
            when(val result = getNaverGeocodeUseCase.invoke(coords)) {
                is NetworkResponse.Success -> {
                    if(result.data.land.name.isBlank()) {
                        if (currentLocation != null) {
                            _geocode.value = convertMyLocationToAddress(currentLocation,context)
                        }
                    }else {
                        Log.d("naverResult","${result.data.region.area3.name} ${result.data.land.name}")
                        _geocode.value = "${result.data.region.area3.name} ${result.data.land.name}"
                    }
                }
                is NetworkResponse.Error -> {
                    Log.d("naverResult", result.toString())
                }
            }
        }
    }

    private fun convertMyLocationToAddress(latLng: LatLng, context: Context) : String {
        val geocoder = Geocoder(context)
        val address : List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude,5)
        val position = address[0].getAddressLine(0).split(" ")
        val gu = position[2]
        val dong = position[3]

        return "$gu $dong"
    }



}