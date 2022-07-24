package com.mashup.damgledamgle.presentation.feature.home

import java.util.concurrent.TimeUnit

object TimeUtil {
    private const val TIME_FORMAT = "%02d:%02d:%02d"

    fun formatTime(millisRemaining: Long): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toHours(millisRemaining),
        TimeUnit.MILLISECONDS.toMinutes(millisRemaining) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(millisRemaining)
        ),
        TimeUnit.MILLISECONDS.toSeconds(millisRemaining) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millisRemaining)
        )
    )

}