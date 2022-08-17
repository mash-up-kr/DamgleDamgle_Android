package com.mashup.damgledamgle.domain.usecase.onboarding

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.OnboardingRepository
import javax.inject.Inject

/**
 *  GetRandomNickNameUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetRandomNickNameUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(adjective: String? = null, noun: String? = null): Result<NickName> {
        return onboardingRepository.getNickName(adjective, noun)
    }
}
