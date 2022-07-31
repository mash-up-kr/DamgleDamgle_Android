package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionBox
import com.mashup.damgledamgle.ui.theme.*
import com.mashup.damgledamgle.util.TimeUtil.getFormattedTimeDiff

@Composable
fun DamgleStoryBox(
    damgleStoryBoxState: DamgleStoryBoxModel,
    onClickNowReaction: () -> Unit,
    onClickReaction: (Reaction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = damgleStoryBoxState.placeName,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Gray1000
        )
        Spacer(modifier = Modifier.height(7.dp))
        Row(modifier = Modifier.height(16.dp)) {
            Text(
                text = damgleStoryBoxState.writer,
                fontSize = 13.sp
            )
            if (damgleStoryBoxState.isMine)
                Text(
                    text = " · ME",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            Spacer(modifier = Modifier.width(8.dp))
            Divider(
                color = Gray800,
                thickness = 1.dp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = getFormattedTimeDiff(damgleStoryBoxState.dateTime),
                color = Yellow,
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .height(348.dp)
                .width(335.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Gray400
        ) {
            Column {
                Text(
                    text = damgleStoryBoxState.content,
                    fontSize = 16.sp,
                    maxLines = 6,
                    modifier = Modifier
                        .padding(end = 24.dp, start = 24.dp, top = 24.dp)
                        .weight(1f),
                )
                DamgleStoryReactionBox(
                    modifier = Modifier,
                    damgleStoryBoxState.reactions
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ReactionBox(
            damgleStoryBoxState.reactionBoxState,
            { onClickNowReaction() },
            { reaction -> onClickReaction(reaction) }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}
