package com.mashup.damgledamgle.commonModel

import com.mashup.damgledamgle.domain.entity.Damgle
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
                reactionSummary = convertReactionEntity(it.reactionSummary),
                reactionOfMine = it.reactionOfMine?.let { it1 -> convertMyReactionEntity(it1) },
                reports = convertReportEntity(it.reports),
                createdAt = it.createAt,
                updatedAt = it.updateAt
            )
        }
    }

    private fun mapToModel(entity: Damgle.Reaction): DamgleModel.Reaction {
        return DamgleModel.Reaction(
            userNo = entity.userNo,
            nickName = entity.nickName,
            type = entity.type
        )
    }

    private fun convertReactionEntity(spec: List<Damgle.ReactionSummary>) : List<DamgleModel.ReactionSummary> {
        return spec.map {
            DamgleModel.ReactionSummary(
                type = it.type,
                count = it.count
            )
        }
    }

    private fun convertMyReactionEntity(spec: Damgle.ReactionOfMine) : DamgleModel.ReactionOfMine {
        return DamgleModel.ReactionOfMine(
            userNo = spec.userNo,
            nickname = spec.nickname,
            type = spec.type
        )
    }

    private fun convertReportEntity(spec: List<Damgle.Reports>) : List<DamgleModel.Reports> {
        return spec.map {
            DamgleModel.Reports(
                userNo = it.userNo,
                createdAt = it.createdAt
            )
        }
    }
}