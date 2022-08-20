package com.mashup.damgledamgle.presentation.feature.mypage.model.mapper

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.presentation.feature.mypage.model.DamgleModel
import javax.inject.Inject

/**
 *  DamgleMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 MashUp All rights reserved.
 */

class DamgleMapper @Inject constructor() {
    fun mapToModel(entity: List<Damgle>): List<DamgleModel> {
        return entity.map {
            DamgleModel(
                id = it.id,
                userNo = it.userNo,
                nickName = it.nickName,
                x = it.x,
                y = it.y,
                content = it.content,
                reactions = it.reactions.map { mapToModel(it) },
                createdAt = it.createAt,
                updatedAt = it.updateAt
            )
        }
    }

    fun mapToModel(entity: Damgle.Reaction): DamgleModel.ReactionModel {
        return DamgleModel.ReactionModel(
            userNo = entity.userNo,
            nickName = entity.nickName,
            type = entity.type
        )
    }
}