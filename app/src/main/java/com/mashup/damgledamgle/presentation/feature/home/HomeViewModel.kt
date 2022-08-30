package com.mashup.damgledamgle.presentation.feature.home

import androidx.lifecycle.*
import com.mashup.damgledamgle.domain.entity.base.launchWithResult
import com.mashup.damgledamgle.domain.usecase.home.*
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.util.TimeUtil.dateFormat
import com.mashup.damgledamgle.util.TimeUtil.getDateDiff
import com.mashup.damgledamgle.util.TimeUtil.getTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNaverGeocodeUseCase: GetNaverGeocodeUseCase,
    private val getLastEntryDamgleDayUseCase: GetLastEntryDamgleDayUseCase,
    private val setLastEntryDamgleDayUseCase: SetLastEntryDamgleDayUseCase
) : ViewModel() {

    private var _locationTitle = MutableLiveData("")
    val locationTitle: LiveData<String> = _locationTitle
    private val locationGeocodeState = MutableStateFlow<ViewState<String>>(ViewState.Loading)


    fun getNaverGeocode(coords : String) {
        viewModelScope.launch {
            launchWithResult(
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

    fun checkEntryAfterDamgleDay(): Boolean {
        if(getLastEntryDamgleDayUseCase.invoke() != "") {
            val lastEntryDay = dateFormat(getLastEntryDamgleDayUseCase.invoke())
            val today = dateFormat(getTodayDate())
            if(lastEntryDay != null && today != null)
                return lastEntryDay.year != today.year || lastEntryDay.month < today.month
        }
        return false
    }

    fun getLastEntryDamgleDay(): String {
        val lastEntryDay = getLastEntryDamgleDayUseCase.invoke()
        if(lastEntryDay != "") return getDateDiff(lastEntryDay)
        return ""
    }

    fun setLastEntryDamgleDay() {
        setLastEntryDamgleDayUseCase.invoke(getTodayDate())
    }

}
