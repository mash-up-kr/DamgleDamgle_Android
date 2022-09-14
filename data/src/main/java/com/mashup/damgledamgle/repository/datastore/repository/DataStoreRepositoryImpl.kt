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
    companion object {
        private const val DATASTORE_FILE_NAME = "DamgleDamgle_DataStore"

        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_FILE_NAME)

    override suspend fun setToken(accessToken: String, refreshToken: String) {
        setStringPreference(KEY_ACCESS_TOKEN, accessToken)
        setStringPreference(KEY_REFRESH_TOKEN, refreshToken)
    }

    override suspend fun getAccessToken(): String {
        return getStringPreferenceOnce(KEY_ACCESS_TOKEN) ?: ""
    }

    override suspend fun getRefreshToken(): String {
        return getStringPreferenceOnce(KEY_REFRESH_TOKEN) ?: ""
    }

    private fun getIntPreference(key: String): Flow<Int?> {
        return getDataStore(intPreferencesKey(key))
    }

    private fun getBooleanPreference(key: String): Flow<Boolean?> {
        return getDataStore(booleanPreferencesKey(key))
    }

    private fun getStringPreference(key: String): Flow<String?> {
        return getDataStore(stringPreferencesKey(key))
    }

    private suspend fun getIntPreferenceOnce(key: String): Int? {
        return getDataStore(intPreferencesKey(key)).first()
    }

    private suspend fun getBooleanPreferenceOnce(key: String): Boolean? {
        return getDataStore(booleanPreferencesKey(key)).first()
    }

    private suspend fun getStringPreferenceOnce(key: String): String? {
        return getDataStore(stringPreferencesKey(key)).first()
    }

    private suspend fun setIntPreference(key: String, value: Int) {
        return setDataStore(intPreferencesKey(key), value)
    }

    private suspend fun setBooleanPreference(key: String, value: Boolean) {
        return setDataStore(booleanPreferencesKey(key), value)
    }

    private suspend fun setStringPreference(key: String, value: String) {
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
