package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.ReadMarkerEntity
import com.mashup.damgledamgle.room.model.ReadMarker

fun readMarkerMapper(readMarkerList: ReadMarkerEntity) : ReadMarker {
    return ReadMarker(readMarkerList.id)
}
