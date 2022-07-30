package com.mashup.damgledamgle.presentation.feature.home

import android.icu.util.Calendar
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.map.MarkerInfo
import com.mashup.damgledamgle.presentation.feature.home.timer.TimeUtil


class HomeViewModel : ViewModel() {

    private var countDownTimer: CountDownTimer? = null
    var totalDiffTime = 0L
    private val _time = MutableLiveData("")
    val time: LiveData<String> = _time

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