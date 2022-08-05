package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryReactionState
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionWithCount

// 이모지의 위치인 x, y는 왼쪽 아래를 기준으로 합니다.
// 카운트의 위치인 countX, countY는 이모지의 왼쪽 위, 사이즈 100을 기준으로 합니다.
data class ReactionOrderState(
    val x: Int,
    val y: Int,
    val countX: Int,
    val countY: Int,
    val rotate: Float,
    val size: Int,
)

// countX가 아님
private val reactionPositionByOrder = mapOf(
    1 to ReactionOrderState(12, -5, 86, 19, -14f, 100),
    2 to ReactionOrderState(138, -10, 100, 75, 9f, 80),
    3 to ReactionOrderState(209, -89, -3, 17, 0f, 100),
    4 to ReactionOrderState(103, -115, -32, 20, -11f, 60),
    5 to ReactionOrderState(248, -36, 82, 8, -14f, 48),
)

private val specialReactionPositionCase = mapOf(
    (Reaction.LIKE to 1) to (-9 to 17),
    (Reaction.LIKE to 2) to (-9 to 17),
    (Reaction.LIKE to 3) to (-9 to 17),
    (Reaction.LIKE to 4) to (-9 to 17),
    (Reaction.LIKE to 5) to (-9 to 17),
    (Reaction.BEST to 1) to (82 to 11),
    (Reaction.BEST to 2) to (82 to 11),
    (Reaction.BEST to 3) to (82 to 11),
    (Reaction.BEST to 5) to (82 to 11),
    (Reaction.ANGRY to 2) to (-18 to 29),
    (Reaction.ANGRY to 3) to (-18 to 29),
    (Reaction.ANGRY to 5) to (-18 to 29),
)

@Composable
fun MultiReaction(modifier: Modifier, reactions: Map<Reaction, DamgleStoryReactionState>) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        Reaction.values().forEach { reaction ->
            val order = reactions[reaction]?.order ?: 0
            if (reactions[reaction] != null)
                ReactionWithCount(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(
                            x = (reactionPositionByOrder[order]?.x ?: 0).dp,
                            y = (reactionPositionByOrder[order]?.y ?: 0).dp
                        ),
                    reaction = reaction,
                    count = reactions[reaction]?.count ?: 0,
                    reactionRotate = reactionPositionByOrder[reactions[reaction]?.order]?.rotate ?: 0f,
                    reactionSize = reactionPositionByOrder[reactions[reaction]?.order]?.size ?: 100,
                    countX = specialReactionPositionCase[reaction to order]?.first ?: reactionPositionByOrder[order]?.countX ?: 0,
                    countY = specialReactionPositionCase[reaction to order]?.second ?: reactionPositionByOrder[order]?.countY ?: 0
                )
        }
    }
}
