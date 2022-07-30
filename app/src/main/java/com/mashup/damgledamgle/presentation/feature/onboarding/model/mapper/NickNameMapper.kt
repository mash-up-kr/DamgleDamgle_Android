package com.mashup.damgledamgle.presentation.feature.onboarding.model.mapper

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.presentation.feature.onboarding.model.NickNameModel
import javax.inject.Inject

/**
 *  NickNameMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class NickNameMapper @Inject constructor() {
    fun mapToModel(entity: NickName): NickNameModel {
        return NickNameModel(
            fullName = entity.name,
            adjective = entity.adjective,
            noun = entity.noun,
            nth = entity.nth
        )
    }
}
