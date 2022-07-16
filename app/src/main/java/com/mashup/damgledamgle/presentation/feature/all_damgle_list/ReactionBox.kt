package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R

@Composable
fun ReactionBox(
        reactionBoxState: ReactionBoxState,
        onClickNowReaction: () -> Unit,
        onClickReaction: (Reaction) -> Unit,
) {

    val offsetBetweenReactionHolder = 8.dp
    val mainReactionHolderWidth = 74.dp
    val baseReactionHolderWidth = 40.dp

    val animatedBoxExtendedStateDp by animateDpAsState(
            if (reactionBoxState.isExtended) baseReactionHolderWidth + offsetBetweenReactionHolder
            else 0.dp
    )

    Box(modifier = Modifier.fillMaxWidth()) {

        Reaction.values().forEachIndexed { index, reaction ->
            ReactionHolder(
                    modifier = Modifier
                            .offset(
                                    x = ((animatedBoxExtendedStateDp * (index) +
                                            if (reactionBoxState.isExtended) mainReactionHolderWidth + offsetBetweenReactionHolder else 0.dp)) * -1
                            )
                            .align(Alignment.BottomEnd),
                    onClickReactionHolder = { onClickReaction(reaction) },
                    when (reaction) {
                        reactionBoxState.selectedReaction -> reaction.selectedDrawableRes
                        else -> reaction.unselectedDrawableRes
                    }
            )
        }

        ReactionHolder(
                modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .width(mainReactionHolderWidth),
                onClickReactionHolder = { onClickNowReaction() },
                when {
                    reactionBoxState.selectedReaction == null -> R.drawable.ic_best_inactive
                    reactionBoxState.isExtended -> reactionBoxState.selectedReaction.inactivateDrawableRes
                    else -> reactionBoxState.selectedReaction.selectedDrawableRes
                }
        )
    }
}
