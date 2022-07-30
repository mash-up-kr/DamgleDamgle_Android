package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.ReadMarkerEntity

interface ReadMarkerRepository {
    suspend fun getReadMarkerId(id : Int) : Boolean
    suspend fun addReadMarkerId(readMarkerEntity: ReadMarkerEntity)
}