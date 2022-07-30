package com.mashup.damgledamgle.domain.usecase.user

import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.UserRepository
import com.mashup.damgledamgle.domain.usecase.token.SetTokenUseCase
import javax.inject.Inject

/**
 *  DeleteUserProfileUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class DeleteUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val setTokenUseCase: SetTokenUseCase
) {
    suspend operator fun invoke(): NetworkResponse<String> {
        val result = userRepository.deleteUserProfile()
        setTokenUseCase("", "")

        return result
    }
}
