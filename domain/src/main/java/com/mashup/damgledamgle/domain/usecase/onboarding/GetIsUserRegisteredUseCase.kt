package com.mashup.damgledamgle.domain.usecase.onboarding

import com.mashup.damgledamgle.domain.repository.DataStoreRepository
import javax.inject.Inject

/**
 *  GetIsUserRegisteredUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetIsUserRegisteredUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Boolean {
        return dataStoreRepository.getBooleanPreferenceOnce(KEY_IS_REGISTER) ?: false
    }

    companion object {
        private const val KEY_IS_REGISTER = "KEY_IS_REGISTER"
    }
}
