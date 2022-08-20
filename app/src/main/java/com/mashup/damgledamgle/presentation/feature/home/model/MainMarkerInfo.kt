package com.mashup.damgledamgle.presentation.feature.home.model

import com.mashup.damgledamgle.domain.entity.Damgle
import com.naver.maps.geometry.LatLng

data class MarkerModel(
    var bound : Bound,
    var damgle : Damgle

)

data class Bound(
    var top : Double,
    var bottom : Double,
    var left : Double,
    var right : Double
)

data class GroupMarkerInfo(
    var mainIcon : String,
    var position : LatLng,
    var count : Int,
    var storyId : List<String>,
    var bound: Bound
)
