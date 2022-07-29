package com.mashup.damgledamgle.repository.datastore.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mashup.damgledamgle.repository.datastore.model.ReadMarkerCheck

@Dao
interface ReadMarkerCheckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadMarkerId(readCheck: ReadMarkerCheck)

    @Transaction
    @Query("SELECT * FROM readCheckTable WHERE id = :id")
    fun getReadMarkerId(id: Int) : ReadMarkerCheck

}
