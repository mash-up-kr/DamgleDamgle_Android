package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

data class NaverGeocodeResponse(
    @Json(name = "results") val results : List<GeocodeResult>
)

data class GeocodeResult(
    @Json(name = "region") val region : GeocodeRegion,
    @Json(name = "land") val land : GeocodeLand
)

data class GeocodeRegion(
    @Json(name = "area2") val area2 : GeocodeLand,
    @Json(name = "area3") val area3 : GeocodeLand,
)

data class GeocodeLand(
    @Json(name = "name") val name : String
)
