package com.mashup.damgledamgle.domain.usecase.token

import com.mashup.damgledamgle.domain.repository.DataStoreRepository
import javax.inject.Inject

/**
 *  GetTokenUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
){
    suspend operator fun invoke(): String {
        return dataStoreRepository.getAccessToken()
    }
}
