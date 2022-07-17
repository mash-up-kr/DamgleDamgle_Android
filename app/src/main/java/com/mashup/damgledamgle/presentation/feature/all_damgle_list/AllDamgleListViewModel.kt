package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionBoxState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class AllDamgleListViewModel @Inject constructor() : ViewModel() {

    var damgleSortStrategy by mutableStateOf(DamgleStorySort.LATEST)

    var reactionBoxState by mutableStateOf(
        mutableMapOf(
            "1" to ReactionBoxState(Reaction.AMAZING, false),
            "2" to ReactionBoxState(Reaction.AMAZING, false),
            "3" to ReactionBoxState(Reaction.AMAZING, false),
            "4" to ReactionBoxState(Reaction.AMAZING, false),
            "5" to ReactionBoxState(Reaction.AMAZING, false),
            "6" to ReactionBoxState(Reaction.AMAZING, false),
            "7" to ReactionBoxState(Reaction.AMAZING, false),
            "8" to ReactionBoxState(Reaction.AMAZING, false),
            "9" to ReactionBoxState(Reaction.AMAZING, false),
            "10" to ReactionBoxState(Reaction.AMAZING, false),
            "11" to ReactionBoxState(Reaction.AMAZING, false),
            "12" to ReactionBoxState(Reaction.AMAZING, false),
            "132" to ReactionBoxState(Reaction.AMAZING, false),
            "4322" to ReactionBoxState(Reaction.AMAZING, false),
            "43322" to ReactionBoxState(Reaction.AMAZING, false),
            "4442" to ReactionBoxState(Reaction.AMAZING, false),
            "442" to ReactionBoxState(Reaction.AMAZING, false),
            "243242423" to ReactionBoxState(Reaction.AMAZING, false)
        )
    )

    fun reactMain(id: String) {
        reactionBoxState = reactionBoxState
            .toMutableMap()
            .apply {
                this[id]?.let {
                    this[id] = it.copy(isExtended = !it.isExtended)
                }
            }
    }

    fun react(id: String, reaction: Reaction) {
        reactionBoxState = reactionBoxState
            .toMutableMap()
            .apply {
                this[id]?.let {
                    this[id] = it.copy(selectedReaction = reaction, isExtended = !it.isExtended)
                }
            }
    }

    fun changeDamgleSortStrategy(damgleSortStrategy: DamgleStorySort) {
        this.damgleSortStrategy = damgleSortStrategy
    }
}
