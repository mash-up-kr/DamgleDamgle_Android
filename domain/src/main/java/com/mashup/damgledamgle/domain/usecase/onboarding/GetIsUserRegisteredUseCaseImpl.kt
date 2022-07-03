package com.mashup.damgledamgle.domain.usecase.onboarding

import com.mashup.damgledamgle.domain.repository.DataStoreRepository
import javax.inject.Inject

/**
 *  GetIsUserRegisteredUseCaseImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetIsUserRegisteredUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : GetIsUserRegisteredUseCase {
    override suspend fun invoke(): Boolean {
        return dataStoreRepository.getDataStoreBooleanOnce(KEY_IS_REGISTER) ?: false
    }

    companion object {
        private const val KEY_IS_REGISTER = "KEY_IS_REGISTER"
    }
}
