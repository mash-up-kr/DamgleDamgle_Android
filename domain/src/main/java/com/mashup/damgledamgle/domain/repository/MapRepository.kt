package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.*
import com.mashup.damgledamgle.domain.entity.base.Result

interface MapRepository {
    suspend fun getReverseGeocoding(coors : String) : Result<GeoResult>
    suspend fun getStoryFeedList(
        top : Double,
        bottom : Double,
        left : Double,
        right : Double
    ) : Result<List<Damgle>>
}