package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.ReactionType
import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.repository.spec.Reactions
import com.mashup.damgledamgle.repository.spec.Stories
import javax.inject.Inject

class StoryFeedMapper @Inject constructor() {
    fun storyFeedMapper(storyFeed : List<Stories>) : List<StoryEntity> {
        return storyFeed.toList().map {
            StoryEntity(
                id = it.id,
                userNo = it.userNo,
                nickname = it.nickname,
                x = it.x,
                y = it.y,
                content = it.content,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                reactions = reactionsMapper(it.reactions)
            )
        }
    }

    private fun reactionsMapper(reaction: List<Reactions>) : List<ReactionType> {
        return reaction.toList().map {
            ReactionType(
                type = it.type
            )
        }
    }
}
