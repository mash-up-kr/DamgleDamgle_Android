package com.mashup.damgledamgle.domain.entity

data class NaverGeocode(
   val results : List<GeoResult>
)

data class GeoResult(
    val region : GeoRegion,
    val land : GeoLand
)

data class GeoRegion(
    val area2 : GeoLand,
    val area3 : GeoLand,
)

data class GeoLand(
    val name : String
)
