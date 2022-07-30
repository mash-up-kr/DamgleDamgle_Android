package com.mashup.damgledamgle.domain.usecase.onboarding

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.OnboardingRepository
import javax.inject.Inject

/**
 *  PickNickNameUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class PickNickNameUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(adjective: String, noun: String): NetworkResponse<NickName> {
        return onboardingRepository.pickNickName(adjective, noun)
    }
}
