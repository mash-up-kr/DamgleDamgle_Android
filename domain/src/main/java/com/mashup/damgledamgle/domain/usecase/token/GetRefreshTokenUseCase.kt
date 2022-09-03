package com.mashup.damgledamgle.domain.usecase.token

import KEY_REFRESH_TOKEN
import com.mashup.damgledamgle.domain.repository.SharedPreferenceRepository
import javax.inject.Inject

/**
 *  GetRefreshTokenUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetRefreshTokenUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {
    operator fun invoke(): String {
        return sharedPreferenceRepository.getString(KEY_REFRESH_TOKEN)
    }
}
