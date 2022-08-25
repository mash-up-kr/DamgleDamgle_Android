package com.mashup.damgledamgle.presentation.feature.all_damgle_list.model

import com.mashup.damgledamgle.enumerate.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.ReactionBoxState

data class DamgleStoryBoxModel(
    val id: String,
    val coordinate: Pair<Double, Double>,
    val addressMain: String? = null,
    val addressSub: String? = null,
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
