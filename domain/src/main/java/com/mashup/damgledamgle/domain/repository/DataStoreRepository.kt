package com.mashup.damgledamgle.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 *  DataStoreRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface DataStoreRepository {
    suspend fun getRefreshToken(): String
    suspend fun getAccessToken(): String
    suspend fun setToken(accessToken: String, refreshToken: String)

//    fun getIntPreference(key: String): Flow<Int?>
//    fun getBooleanPreference(key: String): Flow<Boolean?>
//    fun getStringPreference(key: String): Flow<String?>
//
//    suspend fun getIntPreferenceOnce(key: String): Int?
//    suspend fun getBooleanPreferenceOnce(key: String): Boolean?
//    suspend fun getStringPreferenceOnce(key: String): String?
//
//    suspend fun setIntPreference(key: String, value: Int)
//    suspend fun setBooleanPreference(key: String, value: Boolean)
//    suspend fun setStringPreference(key: String, value: String)
}
