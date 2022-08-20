package com.mashup.damgledamgle.presentation.feature.home.map

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.model.Marker
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.domain.entity.base.launchWithNetworkResult
import com.mashup.damgledamgle.domain.usecase.home.GetStoryFeedUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.home.map.marker.MarkerInfo
import com.mashup.damgledamgle.presentation.feature.home.model.*
import com.mashup.damgledamgle.util.ReactionUtil.getMainIcon
import com.mashup.damgledamgle.util.TimeUtil
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
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

    val storyFeedState = MutableStateFlow<ViewState<ArrayList<GroupMarkerInfo>>>(ViewState.Loading)


    fun getMakerList(): ArrayList<MarkerInfo> {
        val list: ArrayList<MarkerInfo> = arrayListOf()
        list.add(MarkerInfo(R.drawable.ic_heart_small,true, true,37.50759898478214, 127.0589335701742,3))
        list.add(MarkerInfo(R.drawable.ic_angry_small,false, false,37.507360930578762, 127.020016322345,0))
        list.add(MarkerInfo(R.drawable.ic_amazing_small,false, false,37.507332688139535, 127.05799745796462,56))
//        list.add(MarkerInfo(R.drawable.ic_heart_small,false, false,37.56398, 126.97693,0))
//        list.add(MarkerInfo(R.drawable.ic_amazing_small,false, false,37.56406, 126.97778,56))
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
                    top =  37.51281349045091,
                    bottom = 37.50202901315892,
                    left = 127.0548400039805,
                    right = 127.0611215702245
                ),
                suspendOnSuccess = {
                   val groupingStoryData = divideMarkerPosition(
                       top =  37.51281349045091,
                       bottom = 37.50202901315892,
                       left = 127.0548400039805,
                       right = 127.0611215702245,
                        it
                    )
                    Log.d("groupData", groupingStoryData.size.toString())
                    val mainMarker = mappingMarkerInfo(groupingStoryData)
                    storyFeedState.emit(ViewState.Success(mainMarker))

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
        data: List<Damgle>): ArrayList<ArrayList<MarkerModel>> {

        val diffLat = (top - bottom) / 4.0
        val diffLng = (right - left) / 3.0

        Log.d("damgleDiffPosition", "${diffLat}, $diffLng")

        val markerList : ArrayList<ArrayList<MarkerModel>> = arrayListOf()

        for(x in 1..LAT_DIVIDE) {
            for(y in 1..LNG_DIVIDE) {
                val startLat = top - diffLat * (x-1).toDouble()
                val startLng = left + diffLng * (y-1).toDouble()
                val endLat = top - diffLat * x.toDouble()
                val endLng = left + diffLng * y.toDouble()
                val groupList : ArrayList<MarkerModel> = arrayListOf()
                data.forEach { damgle ->
                    if (damgle.y in endLat..startLat && damgle.x in startLng..endLng) {
                        Log.d("damgleplease", "zebal")
                        groupList.add(
                            MarkerModel(
                                bound = Bound(
                                    top = startLat,
                                    bottom = endLat,
                                    left = startLng,
                                    right = endLng
                                ),
                                damgle = damgle
                            )
                        )
                    }
                }
                markerList.add(groupList)
            }
        }

        return markerList
    }

    private fun mappingMarkerInfo(markerList : ArrayList<ArrayList<MarkerModel>>): ArrayList<GroupMarkerInfo> {
        val mainMarkerInfoList : ArrayList<GroupMarkerInfo> = arrayListOf()
        markerList.forEach { groupList ->
            val mainIcon = getMainIcon(groupList)
            val mainPosition = getMarkerMainPosition(groupList)
            val markerListSize = groupList.size
            val storyId = getStoryIdOfList(groupList)
            mainMarkerInfoList.add(
                GroupMarkerInfo(
                    mainIcon = mainIcon,
                    position = mainPosition,
                    count = markerListSize,
                    storyId = storyId,
                    bound = groupList[0].bound //수정 필요
                )
            )
        }
        return mainMarkerInfoList
    }

    private fun getMarkerMainPosition(groupList : List<MarkerModel>): LatLng {
        val positionList : ArrayList<LatLng> = arrayListOf()
        groupList.forEach { storyEntity ->
            positionList.add(LatLng(storyEntity.damgle.y, storyEntity.damgle.x))
        }
        return if(positionList.isNotEmpty()) positionList.random()
        else LatLng(37.51281349045091,127.0548400039805)

    }

    private fun getStoryIdOfList(groupList: List<MarkerModel>): ArrayList<String> {
        val idList : ArrayList<String> = arrayListOf()
        groupList.forEach { storyEntity ->
            idList.add(storyEntity.damgle.id)
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