package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
        Row {

        }
        Spacer(modifier = Modifier.height(32.dp))
        Card(
                modifier = Modifier
                        .height(320.dp)
                        .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Gray400
        ) {

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
