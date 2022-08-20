package com.mashup.damgledamgle.presentation.feature.home.map

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.domain.entity.base.launchWithNetworkResult
import com.mashup.damgledamgle.domain.usecase.home.GetStoryFeedUseCase
import com.mashup.damgledamgle.presentation.feature.home.map.marker.MarkerInfo
import com.mashup.damgledamgle.presentation.feature.home.model.MainMarkerInfo
import com.mashup.damgledamgle.util.ReactionUtil.getMainIcon
import com.mashup.damgledamgle.util.TimeUtil
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    private val _oneHourCheck = MutableLiveData(false)
    val oneHourCheck : LiveData<Boolean> = _oneHourCheck

    fun getMakerList(): ArrayList<MarkerInfo> {
        val list: ArrayList<MarkerInfo> = arrayListOf()
        list.add(MarkerInfo(R.drawable.ic_heart_small,true, true,37.5455113, 127.0657011,0))
        list.add(MarkerInfo(R.drawable.ic_angry_small,false, false,37.5448118, 127.0649041,0))
        list.add(MarkerInfo(R.drawable.ic_amazing_small,false, false,37.54529, 127.06630,56))
        list.add(MarkerInfo(R.drawable.ic_heart_small,false, false,37.56398, 126.97693,0))
        list.add(MarkerInfo(R.drawable.ic_amazing_small,false, false,37.56406, 126.97778,56))
        return list
    }

    /**
     * 서버에서 값 받아오기 완료
     * x,y 계산해서 데이터 mapping 해주기
     */
    fun getStoryFeedList(
        top : Double,
        bottom : Double,
        left : Double,
        right : Double
    ) {
        viewModelScope.launch {
            launchWithNetworkResult(
                result = getStoryFeedUseCase.invoke(
                    top =  37.51,
                    bottom = 37.45,
                    left = 127.033,
                    right = 127.037
                ),
                suspendOnSuccess = {
                   val groupingStoryData = divideMarkerPosition(
                        top,
                        bottom,
                        left,
                        right,
                        it
                    )
                    val mainMarker = mappingMarkerInfo(groupingStoryData)

                },
                suspendOnError = {

                }
            )
        }
    }


    private fun divideMarkerPosition(
        top: Double,
        bottom: Double,
        left: Double,
        right: Double,
        data: List<Damgle>): ArrayList<ArrayList<Damgle>> {
        val diffLat = (top - bottom) / LAT_DIVIDE // 4
        val diffLng = (right - left) / LNG_DIVIDE // 3
        val markerList : ArrayList<ArrayList<Damgle>> = arrayListOf()

        CoroutineScope(Dispatchers.Default).launch {
            for(x in 1..LAT_DIVIDE) {
                for(y in 1..LNG_DIVIDE) {
                    val startLat = top + diffLat * (x-1)
                    val startLng = left + diffLng * (y-1)
                    val latRang = top + diffLat * x
                    val lngRang = left + diffLng * y

                    val groupList : ArrayList<Damgle> = arrayListOf()
                    data.forEach { damgle ->
                        if (damgle.x in startLat..latRang && damgle.y in startLng..lngRang) {
                            groupList.add(damgle)
                        }
                    }
                    markerList.add(groupList)
                }
            }
        }

        return markerList
    }

    private fun mappingMarkerInfo(markerList : ArrayList<ArrayList<Damgle>>): ArrayList<MainMarkerInfo> {
        val mainMarkerInfoList : ArrayList<MainMarkerInfo> = arrayListOf()
        markerList.forEach { groupList ->
            val mainIcon = getMainIcon(groupList)   //대표아이콘 계산
            val mainPosition = getMarkerMainPosition(groupList)  // 대표 위치 - 랜덤으로 돌리기
            val markerListSize = groupList.size // 리스트 개수 저장
            val storyId = getStoryIdOfList(groupList)
            mainMarkerInfoList.add(
                MainMarkerInfo(
                    mainIcon = mainIcon,
                    position = mainPosition,
                    count = markerListSize,
                    storyId = storyId
                )
            )
        }
        return mainMarkerInfoList
    }

    private fun getMarkerMainPosition(groupList : List<Damgle>): LatLng {
        val positionList : ArrayList<LatLng> = arrayListOf()
        groupList.forEach { storyEntity ->
            positionList.add(LatLng(storyEntity.x, storyEntity.y))
        }
        return positionList.random()
    }

    private fun getStoryIdOfList(groupList: List<Damgle>): ArrayList<String> {
        val idList : ArrayList<String> = arrayListOf()
        groupList.forEach { storyEntity ->
            idList.add(storyEntity.id)
        }
        return idList
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(TimeUtil.getCalDiffTime(), 1000) {
            override fun onTick(millisRemaining: Long) {
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
        countDownTimer?.cancel()
    }


    private val LAT_DIVIDE = 4
    private val LNG_DIVIDE = 3

}