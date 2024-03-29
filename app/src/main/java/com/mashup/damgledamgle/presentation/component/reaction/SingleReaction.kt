package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.enumerate.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionWithCount


// 리액션 사이즈 100 * 100 을 기준으로 합니다.
// 리액션 왼쪽 위에서 카운트 왼쪽 끝까지의 x,y 입니다.
private val singleReactionCountPosition = mapOf(
    Reaction.BEST to SingleReactionBundle(80, 6, -11f),
    Reaction.LIKE to SingleReactionBundle(-6, 24, 0f),
    Reaction.AMAZING to SingleReactionBundle(86, 0, 0f),
    Reaction.ANGRY to SingleReactionBundle(-20, 25, 0f),
    Reaction.SAD to SingleReactionBundle(86, 0, 0f),
)

data class SingleReactionBundle(
    val x: Int,
    val y: Int,
    val rotate: Float
)

val singleReactionSize =
    mapOf(
        Reaction.AMAZING to 92f,
        Reaction.LIKE to 118f,
        Reaction.ANGRY to 92f,
        Reaction.SAD to 92f,
        Reaction.BEST to 104f,
    )

@Preview
@Composable
fun SoloReactionDemo() {
    Column {
        SingleReaction(Reaction.AMAZING, 1)
        SingleReaction(Reaction.SAD, 33)
        SingleReaction(Reaction.LIKE, 99)
        SingleReaction(Reaction.ANGRY, 100)
        SingleReaction(Reaction.BEST, 1)
    }
}

@Composable
fun SingleReaction(reaction: Reaction, count: Int) {
    Column {
        Box {
            Image(
                painter = painterResource(id = reaction.soloBackgroundDrawableRes),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
            )
            ReactionWithCount(
                modifier = Modifier.align(Alignment.Center),
                reaction = reaction,
                count = count,
                reactionSize = singleReactionSize[reaction] ?: 100.0f,
                countX = singleReactionCountPosition[reaction]?.x ?: 0,
                countY = singleReactionCountPosition[reaction]?.y ?: 0,
                reactionRotate = singleReactionCountPosition[reaction]?.rotate ?: 0f,
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}
