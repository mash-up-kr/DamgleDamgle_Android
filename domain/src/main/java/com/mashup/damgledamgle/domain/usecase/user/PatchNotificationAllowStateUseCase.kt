package com.mashup.damgledamgle.domain.usecase.user

import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.UserRepository
import javax.inject.Inject

/**
 *  PatchNotificationAllowStateUseCase.kt
 *
 *  Created by Minji Jeong on 2022/08/02
 *  Copyright © 2022 MashUp All rights reserved.
 */

class PatchNotificationAllowStateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResponse<Boolean> {
        return userRepository.switchNotification()
    }
}
