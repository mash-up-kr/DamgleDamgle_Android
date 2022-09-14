package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.enumerate.Reaction
import com.mashup.damgledamgle.presentation.component.reaction.MultiReaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryReactionState
import com.mashup.damgledamgle.ui.theme.Gray600

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
fun DamgleStoryReactionBox(
    modifier: Modifier = Modifier,
    reactions: Map<Reaction, DamgleStoryReactionState>,
    isMine: Boolean = false,
    onClickReport: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(160.dp)
            .width(320.dp),
        contentAlignment = Alignment.Center
    ) {
        val count = reactions.filter { it.value.count > 0 }.count()
        AnimatedVisibility(
            visible = count == 0,
            enter = fadeIn(initialAlpha = 0.3f),
            exit = fadeOut()
        ) {
            EmptyReaction()
        }
        AnimatedVisibility(
            visible = count == 1,
            enter = fadeIn(initialAlpha = 0.3f),
            exit = fadeOut()
        ) {
            reactions.entries.firstOrNull()?.let {
                SingleReaction(it.key, it.value.count)
            }
        }
        AnimatedVisibility(
            visible = count > 1,
            enter = fadeIn(initialAlpha = 0.3f),
            exit = fadeOut()
        ) {
            MultiReaction(modifier = Modifier.fillMaxSize(), reactions)
        }
        if (!isMine)
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-24).dp, y = (-16).dp)
                    .clickable {
                        onClickReport()
                    },
                text = "신고",
                color = Gray600,
                fontSize = 13.sp,
            )
    }
}

data class ReactionBoxState(
    val selectedReaction: Reaction?,
    val isExtended: Boolean
)
