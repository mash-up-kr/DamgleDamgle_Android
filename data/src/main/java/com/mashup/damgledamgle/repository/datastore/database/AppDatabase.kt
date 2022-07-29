package com.mashup.damgledamgle.repository.datastore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mashup.damgledamgle.repository.datastore.dao.ReadMarkerCheckDao
import com.mashup.damgledamgle.repository.datastore.model.ReadMarkerCheck

@Database(
    version = 1, exportSchema = false,
    entities = [
        ReadMarkerCheck::class
    ]
)

@TypeConverters(DamgleTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun readCheckDao(): ReadMarkerCheckDao

    companion object {
        private var dbInstance: AppDatabase? = null

        @Synchronized
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
            return dbInstance
        }
    }

}