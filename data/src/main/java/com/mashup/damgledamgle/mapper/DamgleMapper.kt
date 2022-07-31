package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.repository.spec.DamgleResponse
import javax.inject.Inject

/**
 *  DamgleMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 MashUp All rights reserved.
 */

class DamgleMapper @Inject constructor() {
    fun mapToEntity(spec: DamgleResponse): Damgle {
        return Damgle(
            id = spec.id,
            userNo = spec.userNo,
            nickName = spec.nickName,
            x = spec.x,
            y = spec.y,
            content = spec.content,
            reactions = mapToEntity(spec.reactions),
            createAt = spec.createAt,
            updateAt = spec.updateAt
        )
    }

    fun mapToEntity(spec: List<DamgleResponse.ReactionResponse>): List<Damgle.Reaction> {
        return spec.map {
            Damgle.Reaction(
                userNo = it.userNo,
                nickName = it.nickName,
                type = it.type
            )
        }
    }
}
