package com.mashup.damgledamgle.presentation.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.launchWithNetworkResult
import com.mashup.damgledamgle.domain.usecase.home.GetNaverGeocodeUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNaverGeocodeUseCase: GetNaverGeocodeUseCase
) : ViewModel() {

    val showLoading = MutableLiveData(false)
    val locationGeocodeState = MutableStateFlow<ViewState<String>>(ViewState.Loading)
    var currentLocation : LatLng? = null

    fun getNaverGeocode(coords : String) {
        viewModelScope.launch {
            launchWithNetworkResult(
                result = getNaverGeocodeUseCase.invoke(coords),
                suspendOnSuccess = {
                    val geocode = "${it.region.area3.name} ${it.land.name}"
                    locationGeocodeState.emit(ViewState.Success(geocode))
                },
                suspendOnError = {
                    locationGeocodeState.emit(ViewState.Error(it.message.toString()))
                }
            )
        }
    }
}