package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.repository.spec.response.DamgleResponse
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
            isMine = spec.isMine,
            reactions = mapToEntity(spec.reactions),
            reactionSummary = convertReactionEntity(spec.reactionSummary),
            reactionOfMine = spec.reactionOfMine?.let { convertMyReactionEntity(it) },
            reports = convertReportEntity(spec.reports),
            createAt = spec.createdAt,
            updateAt = spec.updatedAt,
            address1 = spec.address1 ?: "",
            address2 = spec.address2 ?: "",
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

    private fun convertReactionEntity(spec: List<DamgleResponse.ReactionSummary>): List<Damgle.ReactionSummary> {
        return spec.map {
            Damgle.ReactionSummary(
                type = it.type,
                count = it.count
            )
        }
    }

    private fun convertMyReactionEntity(spec: DamgleResponse.ReactionOfMine): Damgle.ReactionOfMine {
        return Damgle.ReactionOfMine(
            userNo = spec.userNo,
            nickname = spec.nickname,
            type = spec.type
        )
    }

    private fun convertReportEntity(spec: List<DamgleResponse.Reports>): List<Damgle.Reports> {
        return spec.map {
            Damgle.Reports(
                userNo = it.userNo,
                createdAt = it.createdAt
            )
        }
    }
}
