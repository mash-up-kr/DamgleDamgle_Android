package com.mashup.damgledamgle.presentation.feature.home.map

import android.icu.util.Calendar
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun getCalendarLastDay(): String {
        var restTime = ""
        val calendar = Calendar.getInstance()
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val today = calendar.get(Calendar.DATE).toString().toInt()
        val diff = lastDay - today

        totalDiffTime = TimeUtil.getCalDiffTime(calendar)
        if(totalDiffTime != 0L) restTime = TimeUtil.formatRestTime(totalDiffTime)

        return if (diff < 1) restTime else "D-$diff"
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(totalDiffTime, 1000) {
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