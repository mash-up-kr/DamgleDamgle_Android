package com.mashup.damgledamgle.domain.repository

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

    suspend fun setId(id: Int)
    suspend fun getId(): Int
}
