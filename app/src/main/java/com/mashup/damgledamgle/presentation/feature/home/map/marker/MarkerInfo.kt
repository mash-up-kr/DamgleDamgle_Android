package com.mashup.damgledamgle.presentation.feature.home.map.marker


/**
 * 테스트를 위한 data class
 */
data class MarkerInfo(
    val resId: Int,
    val isRead: Boolean,
    val isMine :Boolean,
    val latitude: Double,
    val longitude: Double,
    val size : Int
)
