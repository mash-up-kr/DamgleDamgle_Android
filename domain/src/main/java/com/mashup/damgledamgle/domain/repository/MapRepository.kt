package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.base.Result

interface MapRepository {
    fun getLastEntryDamgleDay() : String
    fun setLastEntryDamgleDay(date : String)
    suspend fun getReverseGeocoding(coors : String) : Result<GeoResult>
    suspend fun getStoryFeedList(
        top : Double,
        bottom : Double,
        left : Double,
        right : Double
    ) : Result<List<Damgle>>
}