package com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.enumerate.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.ReactionBoxState
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.Gray400

@Composable
fun ReactionBox(
    reactionBoxState: ReactionBoxState,
    onClickNowReaction: () -> Unit,
    onClickReaction: (Reaction) -> Unit
) {

    val offsetBetweenReactionHolder = 8.dp
    val mainReactionHolderWidth = 74.dp
    val baseReactionHolderWidth = 40.dp

    val animatedBoxExtendedStateDp by animateDpAsState(
        if (reactionBoxState.isExtended) baseReactionHolderWidth + offsetBetweenReactionHolder
        else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    val animatedColor by animateColorAsState(
        if (reactionBoxState.isExtended || animatedBoxExtendedStateDp != 0.dp) Gray400
        else Black,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    )


    Box(modifier = Modifier.fillMaxWidth()) {

        Reaction.values().forEachIndexed { index, reaction ->
            ReactionHolder(
                modifier = Modifier
                    .offset(
                        x = ((animatedBoxExtendedStateDp * (index + 1) + mainReactionHolderWidth - baseReactionHolderWidth)) * -1
                    )
                    .align(Alignment.BottomEnd),
                boxColor = Black,
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
            boxColor = animatedColor,
            onClickReactionHolder = { onClickNowReaction() },
            when {
                reactionBoxState.selectedReaction == null -> R.drawable.ic_best_inactive
                reactionBoxState.isExtended || animatedBoxExtendedStateDp != 0.dp -> reactionBoxState.selectedReaction.inactivateDrawableRes
                else -> reactionBoxState.selectedReaction.selectedDrawableRes
            }
        )
    }
}
