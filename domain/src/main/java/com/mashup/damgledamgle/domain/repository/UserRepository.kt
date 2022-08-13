package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.UserProfile
import com.mashup.damgledamgle.domain.entity.base.Result

/**
 *  UserRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface UserRepository {
    suspend fun getUserProfile(): Result<UserProfile>
    suspend fun deleteUserProfile(): Result<String>
    suspend fun switchNotification(): Result<Boolean>
}
