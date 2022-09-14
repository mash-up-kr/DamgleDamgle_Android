package com.mashup.damgledamgle.domain.repository

/**
 *  TokenPreferenceRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface TokenPreferenceRepository {
    fun getRefreshToken(): String
    fun getAccessToken(): String
    fun setToken(accessToken: String, refreshToken: String)
}
