package com.mashup.damgledamgle.domain.repository

/**
 *  TokenRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface TokenRepository {
    fun getToken(): String
    fun setToken(token: String)
}
