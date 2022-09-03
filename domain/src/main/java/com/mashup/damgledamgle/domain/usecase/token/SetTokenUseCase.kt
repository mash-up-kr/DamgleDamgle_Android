package com.mashup.damgledamgle.domain.usecase.token

import KEY_ACCESS_TOKEN
import KEY_REFRESH_TOKEN
import com.mashup.damgledamgle.domain.repository.SharedPreferenceRepository
import javax.inject.Inject

/**
 *  SetTokenUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class SetTokenUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
){
    operator fun invoke(accessToken: String, refreshToken: String) {
        sharedPreferenceRepository.setString(KEY_ACCESS_TOKEN, accessToken)
        sharedPreferenceRepository.setString(KEY_REFRESH_TOKEN, refreshToken)
    }
}
