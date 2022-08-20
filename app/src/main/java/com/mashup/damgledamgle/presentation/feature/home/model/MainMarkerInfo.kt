package com.mashup.damgledamgle.presentation.feature.home.model

import com.naver.maps.geometry.LatLng

data class MainMarkerInfo(
    var mainIcon : String,
    var position : LatLng,
    var count : Int,
    var storyId : List<String>
)
