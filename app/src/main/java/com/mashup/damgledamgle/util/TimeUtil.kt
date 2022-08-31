package com.mashup.damgledamgle.util

import android.icu.util.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object TimeUtil {

    private fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

    fun dateFormat(date: String): LocalDate? {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getTodayDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = if(calendar.get(Calendar.MONTH)+1 < 10) {
            "0${calendar.get(Calendar.MONTH)+1}"
        } else {
            (calendar.get(Calendar.MONTH)+1).toString()
        }
        val day = if(calendar.get(Calendar.DATE) < 10) {
            "0${calendar.get(Calendar.DATE)}"
        } else {
            calendar.get(Calendar.DATE).toString()
        }
        return "$year-$month-$day"
    }

    fun getDateDiff(date : String): String {
        val saveDate = dateFormat(date)
        val value = when(val diffDateFromFirst = saveDate!!.dayOfMonth - 1) {
            0 -> "오늘"
            1 ->  "어제"
            else -> "${diffDateFromFirst}일 전에"
        }
        return value
    }

    fun getFormattedTimeDiff(now: Long): String {
        return when (val timeDiff = getCurrentTime() - now) {
            in (0..MINUTE_MILLISECOND) -> "${timeDiff / SECOND_MILLISECOND}초전"
            in (0..HOUR_MILLISECOND) -> "${timeDiff / MINUTE_MILLISECOND}분전"
            in (0..DAY_MILLISECOND) -> "${timeDiff / HOUR_MILLISECOND}시간전"
            in (0..WEEK_MILLISECOND) -> "${timeDiff / DAY_MILLISECOND}시간전"
            else -> "${timeDiff / WEEK_MILLISECOND}주전"
        }
    }

    fun formatTimerTime(millisRemaining: Long) : String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toHours(millisRemaining),
        TimeUnit.MILLISECONDS.toMinutes(millisRemaining) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(millisRemaining)
        ),
        TimeUnit.MILLISECONDS.toSeconds(millisRemaining) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millisRemaining)
        )
    )

    fun getCalendarLastDay() : String {
        var restTime = ""
        val calendar = Calendar.getInstance()
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val today = calendar.get(Calendar.DATE).toString().toInt()
        val diff = lastDay - today

        val totalDiffTime = getCalDiffTime()
        if(totalDiffTime != 0L) restTime = formatRestTime(totalDiffTime)

        return if (diff < 1) restTime else "D-$diff"
    }

     fun getCalDiffTime() : Long {
        val calendar = Calendar.getInstance()
        val nowTime = calendar.time

        val eventDate = Calendar.getInstance()
        eventDate[Calendar.YEAR] = calendar.get(Calendar.YEAR)
        eventDate[Calendar.MONTH] = calendar.get(Calendar.MONTH)+1
        eventDate[Calendar.DAY_OF_MONTH] = 1
        eventDate[Calendar.HOUR_OF_DAY] = 0
        eventDate[Calendar.MINUTE] = 0
        eventDate[Calendar.SECOND] = 0

        return eventDate.timeInMillis - nowTime.time
    }

    private fun formatRestTime(totalDiffTime : Long) : String {
        val hours = totalDiffTime / HOUR_MILLISECOND
        val minutes = totalDiffTime / (MINUTE_MILLISECOND) % 60
        val seconds = (totalDiffTime / SECOND_MILLISECOND) % 60

        return "$hours:$minutes:$seconds"
    }

    private const val SECOND_MILLISECOND = 1000
    private const val MINUTE_MILLISECOND = SECOND_MILLISECOND * 60
    private const val HOUR_MILLISECOND = MINUTE_MILLISECOND * 60
    private const val DAY_MILLISECOND = HOUR_MILLISECOND * 24
    private const val WEEK_MILLISECOND = DAY_MILLISECOND * 7
    private const val TIME_FORMAT = "%02d:%02d:%02d"
}
