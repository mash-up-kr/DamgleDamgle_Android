package com.mashup.damgledamgle.repository.datastore.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mashup.damgledamgle.domain.entity.ReadMarkerEntity

@Entity(tableName = "readCheckTable")
data class ReadMarker(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
) {
    fun mapperReadMarker(): ReadMarkerEntity {
        return ReadMarkerEntity(id = id)
    }
}
