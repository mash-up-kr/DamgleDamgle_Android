package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse

/**
 *  DamgleRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface DamgleRepository {
    suspend fun getMyDamgleList(): NetworkResponse<List<Damgle>>
}
