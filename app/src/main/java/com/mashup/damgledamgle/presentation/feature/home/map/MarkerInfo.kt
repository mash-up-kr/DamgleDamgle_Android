package com.mashup.damgledamgle.presentation.feature.home.map

data class MarkerInfo(
    val resId: Int,
    val isRead: Boolean,
    val isMine :Boolean,
    val latitude: Double,
    val longitude: Double,
    val size : Int
)
