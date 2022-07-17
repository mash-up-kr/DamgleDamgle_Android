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
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.*
import com.mashup.damgledamgle.ui.theme.*

@Composable
fun DamgleStoryBox(
    reactionBoxState: ReactionBoxState,
    onClickNowReaction: () -> Unit,
    onClickReaction: (Reaction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "MAPOGU\n" +
                    "YANGHWARO",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Gray1000
        )
        Spacer(modifier = Modifier.height(7.dp))
        Row(modifier = Modifier.height(16.dp)) {
            Text(
                text = "말많은 11번째 코알라",
                fontSize = 13.sp
            )
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
                text = "5분전",
                color = Yellow,
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Gray400
        ) {
            Text(
                text = "안녕하세요! 오늘은 이 카페에서 공감해조팀에서 회의를 하였어요~~ 다들 여기서 팀플해도 좋을듯용~~! 주인이 카공해도 뭐라고 안함~~참고하세요~~~!! 지금 넘 졸린데 오늘 일찍 자고 싶어서 커피를 마시지 않고 버틸 거에요~!!",
                fontSize = 16.sp,
                maxLines = 6,
                modifier = Modifier.padding(end = 24.dp, start = 24.dp, top = 24.dp),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ReactionBox(
            reactionBoxState,
            { onClickNowReaction() },
            { reaction -> onClickReaction(reaction) }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}
