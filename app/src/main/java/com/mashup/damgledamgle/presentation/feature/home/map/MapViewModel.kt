package com.mashup.damgledamgle.presentation.feature.home.map

import android.os.CountDownTimer
import androidx.lifecycle.*
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.map.marker.MarkerInfo
import com.mashup.damgledamgle.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor()
    : ViewModel() {

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

}
