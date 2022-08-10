package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.GeoLand
import com.mashup.damgledamgle.domain.entity.GeoRegion
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.repository.spec.NaverGeocodeResponse

fun geocodeMapper(spec : NaverGeocodeResponse) : GeoResult{
    val result = spec.results[0]
    val area = result.region.area2
    val areaDefault = result.region.area3
    val land = result.land.name
    return GeoResult(
        region = GeoRegion(
            GeoLand(area.name),
            GeoLand(areaDefault.name)),
        land = GeoLand(land)
    )

}
