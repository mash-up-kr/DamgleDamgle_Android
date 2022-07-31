package com.mashup.damgledamgle.domain.usecase.user

import com.mashup.damgledamgle.domain.entity.UserProfile
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.UserRepository
import javax.inject.Inject

/**
 *  GetUserProfileUserCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetUserProfileUserCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResponse<UserProfile> {
        return userRepository.getUserProfile()
    }
}
