package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class AllDamgleListViewModel @Inject constructor() : ViewModel() {

    var reactionBoxState by mutableStateOf(
            mutableMapOf(
                    "1" to ReactionBoxState(Reaction.AMAZING, false),
                    "2" to ReactionBoxState(Reaction.AMAZING, false)
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
}
