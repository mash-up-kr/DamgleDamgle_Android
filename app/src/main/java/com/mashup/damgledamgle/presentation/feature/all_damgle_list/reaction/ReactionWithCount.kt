package com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.presentation.common.ReactionCountCircle


@Preview
@Composable
fun ReactionWithCountDemo() {
    Column {
        ReactionWithCount(Modifier, Reaction.BEST, 100, 1, 1)
        ReactionWithCount(Modifier, Reaction.ANGRY, 11, 100, 100)
        ReactionWithCount(Modifier, Reaction.LIKE, 22, 100, 0)
        ReactionWithCount(Modifier, Reaction.AMAZING, 0, 0, 100)
        ReactionWithCount(Modifier, Reaction.SAD, 22, 50, 50)
    }
}

@Composable
fun ReactionWithCount(
    modifier: Modifier = Modifier,
    reaction: Reaction,
    count: Int,
    countX: Int,
    countY: Int,
    reactionSize: Int = 100,
    reactionRotate: Float = 0f
) {
    if (count > 0)
        Box(modifier) {
            Image(
                painter = painterResource(id = reaction.mainImageDrawableRes),
                contentDescription = null,
                modifier = Modifier
                    .size(reactionSize.dp)
                    .align(Alignment.Center)
                    .rotate(reactionRotate)
            )
            ReactionCountCircle(
                modifier = Modifier.offset(
                    x = (countX * (reactionSize / 100)).dp,
                    y = (countY * (reactionSize / 100)).dp,
                ),
                count
            )
        }
}
