package com.mashup.damgledamgle.domain.usecase.token

import com.mashup.damgledamgle.domain.repository.TokenRepository
import kotlinx.coroutines.channels.ticker
import javax.inject.Inject

/**
 *  SetTokenUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class SetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
){
    operator fun invoke(token: String) {
        tokenRepository.setToken(token)
    }
}
