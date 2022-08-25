package com.mashup.damgledamgle.presentation.feature.home.model

import android.os.Parcelable
import com.mashup.damgledamgle.domain.entity.Damgle
import com.naver.maps.geometry.LatLng
import kotlinx.parcelize.Parcelize

data class MarkerModel(
    var bound : Bound,
    var damgle : ArrayList<Damgle>

)

@Parcelize
class Bound(
    var top : Double,
    var bottom : Double,
    var left : Double,
    var right : Double
) : Parcelable

data class GroupMarkerInfo(
    var mainIcon : String,
    var position : LatLng,
    var count : Int,
    var isMine : Boolean,
    var storyId : List<String>,
    var bound: Bound
)
