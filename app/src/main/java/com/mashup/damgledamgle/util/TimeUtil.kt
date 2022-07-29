package com.mashup.damgledamgle.util

object TimeUtil {

    private fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

    fun getFormattedTimeDiff(now: Long) {
        when (val timeDiff = now - getCurrentTime()) {
            in (0..MINUTE_MILLISECOND) -> "${timeDiff / SECOND_MILLISECOND}초전"
            in (0..HOUR_MILLISECOND) -> "${timeDiff / MINUTE_MILLISECOND}분전"
            in (0..DAY_MILLISECOND) -> "${timeDiff / HOUR_MILLISECOND}시간전"
            in (0..WEEK_MILLISECOND) -> "${timeDiff / HOUR_MILLISECOND}시간전"
            else -> "${timeDiff / MINUTE_MILLISECOND}주전"
        }
    }

    private const val SECOND_MILLISECOND = 1000
    private const val MINUTE_MILLISECOND = SECOND_MILLISECOND * 60
    private const val HOUR_MILLISECOND = MINUTE_MILLISECOND * 60
    private const val DAY_MILLISECOND = HOUR_MILLISECOND * 24
    private const val WEEK_MILLISECOND = DAY_MILLISECOND * 7
}
