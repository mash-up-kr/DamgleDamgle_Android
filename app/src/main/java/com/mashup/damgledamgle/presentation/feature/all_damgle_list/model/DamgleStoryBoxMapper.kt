package com.mashup.damgledamgle.presentation.feature.all_damgle_list.model

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.enumerate.Reaction
import com.mashup.damgledamgle.enumerate.toReaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.ReactionBoxState

object DamgleStoryBoxMapper {
    fun mapToModel(entity: Damgle, reactionBoxState: ReactionBoxState): DamgleStoryBoxModel {
        return DamgleStoryBoxModel(
            id = entity.id,
            coordinate = entity.x to entity.y,
            addressMain = entity.address1,
            addressSub = entity.address2,
            writer = entity.nickName,
            isMine = entity.isMine,
            content = entity.content,
            dateTime = entity.createAt,
            reactions = entity.reactionSummary.let { list ->
                var index = 1
                val map = mutableMapOf<Reaction, DamgleStoryReactionState>()
                list.forEach { summary ->
                    summary.type.toReaction()?.let { reaction ->
                        if (summary.count != 0)
                            map[reaction] = DamgleStoryReactionState(summary.count, index)
                    }
                    index += 1
                }
                map
            },
            reports = entity.reports.map { it.userNo },
            myReaction = entity.reactionOfMine?.type?.toReaction(),
            reactionBoxState = reactionBoxState
        )
    }
}
