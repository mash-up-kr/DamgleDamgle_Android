package com.mashup.damgledamgle.room.dao

import androidx.room.*
import com.mashup.damgledamgle.room.model.ReadMarker

@Dao
interface ReadMarkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadMarkerId(readCheck: ReadMarker)

    @Transaction
    @Query("SELECT * FROM readCheckTable WHERE id = :id")
    fun getReadMarkerId(id: Int) : Boolean?

}
