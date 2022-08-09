package com.mashup.damgledamgle.repository.home

import com.mashup.damgledamgle.domain.entity.ReadMarkerEntity
import com.mashup.damgledamgle.domain.repository.ReadMarkerRepository
import com.mashup.damgledamgle.mapper.readMarkerMapper

import com.mashup.damgledamgle.room.dao.ReadMarkerDao
import javax.inject.Inject

class ReadMarkerRepositoryImpl @Inject constructor(
    private val readMarkerDao: ReadMarkerDao): ReadMarkerRepository {
    override suspend fun getReadMarkerId(id: Int): Boolean {
        return readMarkerDao.getReadMarkerId(id)
    }

    override suspend fun addReadMarkerId(readMarkerEntity: ReadMarkerEntity) {
        readMarkerDao.insertReadMarkerId(readMarkerMapper(readMarkerEntity))
    }
}