package com.mashup.damgledamgle.presentation.feature.home.map

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.launchWithResult
import com.mashup.damgledamgle.domain.usecase.home.GetStoryFeedUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.home.HomeViewModel
import com.mashup.damgledamgle.presentation.feature.home.model.*
import com.mashup.damgledamgle.util.ReactionUtil.getMainIconFromGroupList
import com.mashup.damgledamgle.util.TimeUtil
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getStoryFeedUseCase: GetStoryFeedUseCase
) : ViewModel() {

    private var countDownTimer: CountDownTimer? = null
    private val _time = MutableLiveData("")
    val time: LiveData<String> = _time
    private val _timerStatus = MutableStateFlow(false)
    val timerStatus: StateFlow<Boolean> = _timerStatus
    private val _oneHourCheck = MutableLiveData(false)
    val oneHourCheck : LiveData<Boolean> = _oneHourCheck

    val showLoading = MutableLiveData(false)
    var currentBound : Bound? = null
    var movingBound : LatLng? = null
    val storyFeedState = MutableStateFlow<ViewState<ArrayList<GroupMarkerInfo>>>(ViewState.Loading)
    private val _myLocation = MutableLiveData<LatLng?>(null)
    val myLocation: LiveData<LatLng?> = _myLocation

    fun updateMyLocation(latLng: LatLng?) {
        _myLocation.value = latLng
    }

    fun homeRefreshBtnEvent(
        homeViewModel: HomeViewModel,
        updateLocation: LatLng?
    ) {
        viewModelScope.launch {
            delay(2000)
            homeViewModel.getNaverGeocode(
                "${updateLocation?.longitude},${updateLocation?.latitude}")
            if(currentBound != null) {
                getStoryFeedList(
                    currentBound!!.top,
                    currentBound!!.bottom,
                    currentBound!!.left,
                    currentBound!!.right
                )
            }
            showLoading.value = false
        }
    }

    fun getStoryFeedList(
        top: Double,
        bottom: Double,
        left: Double,
        right: Double
    ) {
        viewModelScope.launch {
            launchWithResult(
                result = getStoryFeedUseCase.invoke(
                    top = top,
                    bottom = bottom,
                    left = left,
                    right = right
                ),
                suspendOnSuccess = {
                    if (it.isNotEmpty()) {
                        val groupingStoryData = divideMarkerPosition(
                            top = top,
                            bottom = bottom,
                            left = left,
                            right = right,
                            it
                        )
                        val mainMarker = mappingMarkerInfo(groupingStoryData)
                        storyFeedState.emit(ViewState.Success(mainMarker))
                    }
                },
                suspendOnError = {
                    Log.d("error_message", it.message.toString())
                    storyFeedState.emit(ViewState.Error(it.message.orEmpty()))
                }
            )
        }
    }


    private fun divideMarkerPosition(
        top: Double,
        bottom: Double,
        left: Double,
        right: Double,
        data: List<Damgle>
    ): ArrayList<MarkerModel> {

        val diffLat = (top - bottom) / LAT_DIVIDE.toDouble()
        val diffLng = (right - left) / LNG_DIVIDE.toDouble()
        Log.d("damgleDiffPosition", "${diffLat}, $diffLng")

        val markerList: ArrayList<MarkerModel> = arrayListOf()

        for (x in 1..LAT_DIVIDE) {
            for (y in 1..LNG_DIVIDE) {
                val startLat = top - diffLat * (x - 1).toDouble()
                val startLng = left + diffLng * (y - 1).toDouble()
                val endLat = top - diffLat * x.toDouble()
                val endLng = left + diffLng * y.toDouble()
                val groupList: ArrayList<Damgle> = arrayListOf()
                data.forEach { damgle ->
                    if (damgle.y in endLat..startLat && damgle.x in startLng..endLng) {
                        groupList.add(damgle)
                    }
                }
                if (groupList.size != 0) {
                    markerList.add(
                        MarkerModel(
                            bound = Bound(
                                top = startLat,
                                bottom = endLat,
                                left = startLng,
                                right = endLng
                            ),
                            groupList
                        )
                    )
                }
            }
        }
        return markerList
    }

    private fun mappingMarkerInfo(markerList: ArrayList<MarkerModel>): ArrayList<GroupMarkerInfo> {
        val mainMarkerInfoList: ArrayList<GroupMarkerInfo> = arrayListOf()
        markerList.forEach { groupList ->
            val mainIcon = getMainIconFromGroupList(groupList.damgle)
            val mainPosition = getMarkerRandomPosition(groupList.damgle)
            val markerListSize = groupList.damgle.size
            val isMine = isMyStoryCheck(groupList.damgle)
            val storyId = getStoryIdOfList(groupList.damgle)
            mainMarkerInfoList.add(
                GroupMarkerInfo(
                    mainIcon = mainIcon,
                    position = mainPosition,
                    count = markerListSize,
                    isMine = isMine,
                    storyId = storyId,
                    bound = groupList.bound
                )
            )
        }
        return mainMarkerInfoList
    }

    private fun isMyStoryCheck(groupList: List<Damgle>): Boolean {
        var isMine = false
        run {
            groupList.forEach {
                if (it.isMine) {
                    isMine = true
                    return@run
                }
            }
        }
        return isMine
    }

    private fun getMarkerRandomPosition(groupList: List<Damgle>): LatLng {
        val positionList: ArrayList<LatLng> = arrayListOf()
        groupList.forEach { storyEntity ->
            positionList.add(LatLng(storyEntity.y, storyEntity.x))
        }
        return positionList.random()
    }

    private fun getStoryIdOfList(groupList: List<Damgle>): ArrayList<String> {
        val idList: ArrayList<String> = arrayListOf()
        groupList.forEach { storyEntity ->
            idList.add(storyEntity.id)
        }
        return idList
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(TimeUtil.getCalDiffTime(), 1000) {
            override fun onTick(millisRemaining: Long) {
                _timerStatus.value = true
                _oneHourCheck.value = TimeUnit.MILLISECONDS.toHours(millisRemaining) < 1
                val hms = TimeUtil.formatTimerTime(millisRemaining)
                _time.value = hms
            }

            override fun onFinish() {
                pauseTimer()
            }
        }.start()

    }

    private fun pauseTimer() {
        _timerStatus.value = false
        countDownTimer?.cancel()

    }


    private val LAT_DIVIDE = 4
    private val LNG_DIVIDE = 3

}
