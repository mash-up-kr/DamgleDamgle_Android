package com.mashup.damgledamgle.presentation.feature.home

import android.content.Context
import android.icu.util.Calendar
import android.location.Address
import android.location.Geocoder
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.home.GetNaverGeocodeUseCase
import com.mashup.damgledamgle.domain.usecase.onboarding.GetRandomNickNameUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.home.map.MarkerInfo
import com.mashup.damgledamgle.presentation.feature.home.timer.TimeUtil
import com.mashup.damgledamgle.presentation.feature.onboarding.model.mapper.NickNameMapper
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNaverGeocodeUseCase: GetNaverGeocodeUseCase) : ViewModel() {

    var showLoading = MutableLiveData(false)

    private val _geocode = MutableLiveData("")
    val geocode: LiveData<String> = _geocode

    fun getNaverGeocode(coords : String) {
        viewModelScope.launch {
            val result = getNaverGeocodeUseCase.invoke(coords)
            when(result) {
                is NetworkResponse.Success -> {
                    Log.d("naverResult", result.data.toString())
                    _geocode.value = "${result.data.region.area3} ${result.data.land.name}"
                }
                is NetworkResponse.Error -> {
                    Log.d("naverResult", result.toString())
                }
            }
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



}