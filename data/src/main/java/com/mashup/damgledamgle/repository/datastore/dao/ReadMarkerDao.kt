package com.mashup.damgledamgle.repository.datastore.dao

import androidx.room.*
import com.mashup.damgledamgle.repository.datastore.model.ReadMarker

@Dao
interface ReadMarkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadMarkerId(readCheck: ReadMarker)

    @Transaction
    @Query("SELECT * FROM readCheckTable WHERE id = :id")
    fun getReadMarkerId(id: Int) : Boolean

}
