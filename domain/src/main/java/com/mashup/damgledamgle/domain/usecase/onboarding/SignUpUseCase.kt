package com.mashup.damgledamgle.domain.usecase.onboarding

import com.mashup.damgledamgle.domain.entity.User
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.OnboardingRepository
import javax.inject.Inject

/**
 *  SignUpUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/28
 *  Copyright © 2022 MashUp All rights reserved.
 */

class SignUpUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(nickName: String): NetworkResponse<User> {
        return onboardingRepository.signUp(nickName)
    }
}
