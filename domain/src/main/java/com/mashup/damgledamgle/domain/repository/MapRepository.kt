package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.domain.entity.StoryFeed

import com.mashup.damgledamgle.domain.entity.base.NetworkResponse

interface MapRepository {
    suspend fun getReverseGeocoding(coors : String) : NetworkResponse<GeoResult>
    suspend fun getStoryFeedList(
        top : Double,
        bottom : Double,
        left : Double,
        right : Double
    ) : NetworkResponse<List<StoryEntity>>
}