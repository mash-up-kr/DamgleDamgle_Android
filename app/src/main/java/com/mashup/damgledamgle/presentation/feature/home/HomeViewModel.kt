package com.mashup.damgledamgle.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.launchWithNetworkResult
import com.mashup.damgledamgle.domain.usecase.home.GetNaverGeocodeUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNaverGeocodeUseCase: GetNaverGeocodeUseCase
) : ViewModel() {

    private val _showLoading = MutableLiveData(false)
    val showLoading: LiveData<Boolean> = _showLoading
    private var _locationTitle = MutableLiveData("")
    val locationTitle: LiveData<String> = _locationTitle
    private val locationGeocodeState = MutableStateFlow<ViewState<String>>(ViewState.Loading)

    init {
        viewStateObserver()
    }

    private fun viewStateObserver() {
        viewModelScope.launch {
            locationGeocodeState.collect { state ->
                when(state) {
                    is ViewState.Loading -> _showLoading.value = true
                    is ViewState.Success -> {
                        _showLoading.value = false
                    }
                    else -> {
                        _showLoading.value = false
                    }
                }
            }
        }
    }

    fun homeRefreshBtnEvent(updateLocation: LatLng?) {
        viewModelScope.launch {
            locationGeocodeState.value = ViewState.Loading
            delay(2000)
            getNaverGeocode(
                "${updateLocation?.longitude},${updateLocation?.latitude}")

        }
    }

    fun getNaverGeocode(coords : String) {
        viewModelScope.launch {
            launchWithNetworkResult(
                result = getNaverGeocodeUseCase.invoke(coords),
                suspendOnSuccess = {
                    val geocode = "${it.region.area3.name} ${it.land.name}"
                    _locationTitle.value = geocode
                    locationGeocodeState.emit(ViewState.Success(geocode)) },
                suspendOnError = {
                    _locationTitle.value = ""
                    locationGeocodeState.emit(ViewState.Error(it.message.toString()))
                }
            )
        }
    }
}