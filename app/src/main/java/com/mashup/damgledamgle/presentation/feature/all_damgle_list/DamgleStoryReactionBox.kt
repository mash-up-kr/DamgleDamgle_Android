package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryReactionState
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction

@Preview
@Composable
fun DamgleStoryBoxReactionsDemo() {
    Column {
//        DamgleStoryReactionBox(reactions = mapOf())
//        DamgleStoryReactionBox(reactions = mapOf(Reaction.BEST to DamgleStoryReactionState(1, 2)))
        Spacer(modifier = Modifier.height(200.dp))
        DamgleStoryReactionBox(
            reactions = mapOf(
                Reaction.BEST to DamgleStoryReactionState(111, 1),
                Reaction.ANGRY to DamgleStoryReactionState(211, 2),
                Reaction.LIKE to DamgleStoryReactionState(211, 3),
                Reaction.AMAZING to DamgleStoryReactionState(211, 4),
                Reaction.SAD to DamgleStoryReactionState(211, 5)
            )
        )
    }
}

@Composable
fun DamgleStoryReactionBox(modifier: Modifier = Modifier, reactions: Map<Reaction, DamgleStoryReactionState>) {
    Box(
        modifier = modifier
            .height(160.dp)
            .width(320.dp),
        contentAlignment = Alignment.Center
    ) {
        when (reactions.filter { it.value.count > 0 }.count()) {
            0 -> EmptyReaction()
            1 -> SingleReaction(reactions.entries.first().key, reactions.entries.first().value.count)
            else -> MultiReaction(modifier = Modifier.fillMaxSize(), reactions)
        }
    }
}
