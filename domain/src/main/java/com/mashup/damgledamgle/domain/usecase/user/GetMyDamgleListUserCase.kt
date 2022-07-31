package com.mashup.damgledamgle.domain.usecase.user

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.DamgleRepository
import javax.inject.Inject

/**
 *  GetMyDamgleListUserCase.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 MashUp All rights reserved.
 */

class GetMyDamgleListUserCase @Inject constructor(
    private val damgleRepository: DamgleRepository,
) {
    suspend operator fun invoke(): NetworkResponse<List<Damgle>> {
        return damgleRepository.getMyDamgleList()
    }
}
