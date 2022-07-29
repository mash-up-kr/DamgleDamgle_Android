package com.mashup.damgledamgle.repository.datastore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "readCheckTable")
data class ReadMarkerCheck(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
