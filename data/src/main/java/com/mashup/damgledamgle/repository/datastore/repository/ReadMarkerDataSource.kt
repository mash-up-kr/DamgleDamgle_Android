package com.mashup.damgledamgle.repository.datastore.repository

import com.mashup.damgledamgle.repository.datastore.model.ReadMarker


interface ReadMarkerDataSource {
    fun getReadMarkerId(id: Int) : Boolean
    fun insertMovies(id : ReadMarker)
}