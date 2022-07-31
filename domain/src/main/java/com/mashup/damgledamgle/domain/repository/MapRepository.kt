package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.GeoResult

import com.mashup.damgledamgle.domain.entity.base.NetworkResponse

interface MapRepository {
    suspend fun getReverseGeocoding(coords : String) :NetworkResponse<GeoResult>
}