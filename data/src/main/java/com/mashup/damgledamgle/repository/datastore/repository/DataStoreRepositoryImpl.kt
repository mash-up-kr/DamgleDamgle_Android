package com.mashup.damgledamgle.repository.datastore.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.mashup.damgledamgle.domain.repository.DataStoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

/**
 *  DataStoreRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DamgleDamgle_DataStore")

    override fun getIntPreference(key: String): Flow<Int?> {
        return getDataStore(intPreferencesKey(key))
    }

    override fun getBooleanPreference(key: String): Flow<Boolean?> {
        return getDataStore(booleanPreferencesKey(key))
    }

    override fun getStringPreference(key: String): Flow<String?> {
        return getDataStore(stringPreferencesKey(key))
    }

    override suspend fun getIntPreferenceOnce(key: String): Int? {
        return getDataStore(intPreferencesKey(key)).first()
    }

    override suspend fun getBooleanPreferenceOnce(key: String): Boolean? {
        return getDataStore(booleanPreferencesKey(key)).first()
    }

    override suspend fun getStringPreferenceOnce(key: String): String? {
        return getDataStore(stringPreferencesKey(key)).first()
    }

    override suspend fun setIntPreference(key: String, value: Int) {
        return setDataStore(intPreferencesKey(key), value)
    }

    override suspend fun setBooleanPreference(key: String, value: Boolean) {
        return setDataStore(booleanPreferencesKey(key), value)
    }

    override suspend fun setStringPreference(key: String, value: String) {
        return setDataStore(stringPreferencesKey(key), value)
    }

    private suspend fun <T> setDataStore(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private fun <T> getDataStore(key: Preferences.Key<T>): Flow<T?> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[key]
            }
    }
}
