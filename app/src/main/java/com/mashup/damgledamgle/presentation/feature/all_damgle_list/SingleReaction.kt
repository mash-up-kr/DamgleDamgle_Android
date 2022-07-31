package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionWithCount


// 리액션 사이즈 100 * 100 을 기준으로 합니다.
// 리액션 왼쪽 위에서 카운트 왼쪽 끝까지의 x,y 입니다.
private val singleReactionCountPosition = mapOf(
    Reaction.BEST to (77 to 2),
    Reaction.LIKE to (-9 to 12),
    Reaction.AMAZING to (79 to -7),
    Reaction.ANGRY to (-9 to 10),
    Reaction.SAD to (79 to -7),
)

val singleReactionSize =
    mapOf(
        Reaction.AMAZING to 92,
        Reaction.LIKE to 104,
        Reaction.ANGRY to 92,
        Reaction.SAD to 92,
        Reaction.BEST to 118,
    )

@Preview
@Composable
fun SoloReactionDemo() {
    Column {
        SingleReaction(Reaction.AMAZING, 1)
        SingleReaction(Reaction.SAD, 33)
        SingleReaction(Reaction.LIKE, 99)
        SingleReaction(Reaction.ANGRY, 100)
        SingleReaction(Reaction.BEST, 0)
    }
}

@Composable
fun SingleReaction(reaction: Reaction, count: Int) {
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
            reactionSize = singleReactionSize[reaction] ?: 100,
            countX = singleReactionCountPosition[reaction]?.first ?: 0,
            countY = singleReactionCountPosition[reaction]?.second ?: 0
        )
    }
}
