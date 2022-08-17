package com.mashup.damgledamgle.presentation.feature.home.map

import android.icu.util.Calendar
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.home.GetStoryFeedUseCase
import com.mashup.damgledamgle.presentation.feature.home.map.marker.MarkerInfo
import com.mashup.damgledamgle.presentation.feature.home.timer.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getStoryFeedUseCase: GetStoryFeedUseCase
) : ViewModel() {

    private var countDownTimer: CountDownTimer? = null
    var totalDiffTime = 0L
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
            when (val result = getStoryFeedUseCase.invoke()) {
                is NetworkResponse.Success -> {
                    divideMarkerPosition(
                        top,
                        bottom,
                        left,
                        right,
                        result.data
                    )
                    Log.d("storyFeedResult", result.data.toString())
                }
                is NetworkResponse.Error -> {}
            }
        }
    }


    private suspend fun divideMarkerPosition(
        top: Double,
        bottom: Double,
        left: Double,
        right: Double,
        data: List<StoryEntity>) {
        val diffLat = (bottom - top) / 4
        val diffLong = (right - left) / 3



    }

    fun getCalendarLastDay(): String {
        val calendar = Calendar.getInstance()
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val today = calendar.get(Calendar.DATE).toString().toInt()
        val diff = lastDay - today

        return if (diff < 1) getCalDiffTime(calendar) else "D-$diff"
    }

    private fun getCalDiffTime(calendar: Calendar): String {
        val nowTime = calendar.time

        val eventDate = Calendar.getInstance()
        eventDate[Calendar.YEAR] = calendar.get(Calendar.YEAR)
        eventDate[Calendar.MONTH] = calendar.get(Calendar.MONTH)+1
        eventDate[Calendar.DAY_OF_MONTH] = 1
        eventDate[Calendar.HOUR_OF_DAY] = 0
        eventDate[Calendar.MINUTE] = 0
        eventDate[Calendar.SECOND] = 0

        totalDiffTime = eventDate.timeInMillis - nowTime.time

        val hours = totalDiffTime / (60 * 60 * 1000)
        val minutes = totalDiffTime / (1000 * 60) % 60
        val seconds = (totalDiffTime / 1000) % 60

        return "$hours:$minutes:$seconds"
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(totalDiffTime, 1000) {
            override fun onTick(millisRemaining: Long) {
                _oneHourCheck.value = TimeUnit.MILLISECONDS.toHours(millisRemaining) < 1
                val hms = TimeUtil.formatTime(millisRemaining)
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

}