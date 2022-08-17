package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.User
import com.mashup.damgledamgle.domain.entity.base.Result

/**
 *  OnboardingRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface OnboardingRepository {
    suspend fun getNickName(adjective: String? = null, noun: String? = null): Result<NickName>
    suspend fun pickNickName(adjective: String, noun: String): Result<NickName>
    suspend fun signUp(nickName: String, notification: Boolean): Result<User>
}
