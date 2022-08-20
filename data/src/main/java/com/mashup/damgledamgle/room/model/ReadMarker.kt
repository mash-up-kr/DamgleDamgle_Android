package com.mashup.damgledamgle.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "readCheckTable")
data class ReadMarker(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
