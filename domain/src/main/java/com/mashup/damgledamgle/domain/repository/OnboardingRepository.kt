package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse

/**
 *  OnboardingRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface OnboardingRepository {
    suspend fun getNickName(): NetworkResponse<NickName>
}
