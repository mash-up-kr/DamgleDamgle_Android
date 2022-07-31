package com.mashup.damgledamgle.presentation.feature.all_damgle_list.model

import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionBoxState

data class DamgleStoryBoxModel(
    val id: String,
    val coordinate: Pair<Int, Int>,
    val placeName: String,
    val writer: String,
    val isMine: Boolean,
    val dateTime: Long,
    val content: String,
    val reactions: Map<Reaction, DamgleStoryReactionState>,
    val myReaction: Reaction?,
    val reactionBoxState: ReactionBoxState
)

data class DamgleStoryReactionState(
    val count: Int,
    val order: Int
)
