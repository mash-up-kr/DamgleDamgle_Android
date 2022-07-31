package com.mashup.damgledamgle.repository.datastore.repository

import com.mashup.damgledamgle.repository.datastore.dao.ReadMarkerDao
import com.mashup.damgledamgle.repository.datastore.model.ReadMarker
import javax.inject.Inject

class ReadMarkerDataStoreImpl @Inject constructor(private val readMarkerDao : ReadMarkerDao) :
    ReadMarkerDataSource {
    override fun getReadMarkerId(id: Int): Boolean {
       return readMarkerDao.getReadMarkerId(id)
    }

    override fun insertMovies(id: ReadMarker) {
        readMarkerDao.insertReadMarkerId(id)
    }
}