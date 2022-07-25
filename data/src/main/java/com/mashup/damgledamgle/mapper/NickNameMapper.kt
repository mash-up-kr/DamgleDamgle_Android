package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.repository.spec.NickNameRequest
import javax.inject.Inject

/**
 *  NickNameMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class NickNameMapper @Inject constructor() {
    fun mapToEntity(spec: NickNameRequest): NickName {
        return NickName(
            name = spec.name,
            adjective = spec.adjective,
            noun = spec.noun,
            nth = spec.nth
        )
    }
}
