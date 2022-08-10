package com.mashup.damgledamgle.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mashup.damgledamgle.room.dao.ReadMarkerDao
import com.mashup.damgledamgle.room.model.ReadMarker

@Database(
    version = 1, exportSchema = false,
    entities = [
        ReadMarker::class
    ]
)

@TypeConverters(DamgleTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun readCheckDao(): ReadMarkerDao


    companion object {
        private var dbInstance: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase? {
            synchronized(AppDatabase::class.java) {
                if (dbInstance == null) {
                    synchronized(AppDatabase::class){
                        dbInstance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "damgle-db"
                        )
                            .build()
                    }
                }
            }
            return dbInstance!!
        }
    }

}