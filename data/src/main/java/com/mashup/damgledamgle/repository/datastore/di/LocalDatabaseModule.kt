package com.mashup.damgledamgle.repository.datastore.di

import android.app.Application

import com.mashup.damgledamgle.repository.datastore.dao.ReadMarkerDao
import com.mashup.damgledamgle.repository.datastore.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Singleton
    @Provides
    fun provideDamgleDatabase(context: Application): AppDatabase? {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providereadCheckDao(appDB: AppDatabase): ReadMarkerDao {
        return appDB.readCheckDao()
    }
}