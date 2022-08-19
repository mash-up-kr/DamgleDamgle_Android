package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.base.Result

interface MapRepository {
    suspend fun getReverseGeocoding(coors : String) : Result<GeoResult>
}