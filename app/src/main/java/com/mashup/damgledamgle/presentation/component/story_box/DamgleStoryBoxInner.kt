package com.mashup.damgledamgle.presentation.component.story_box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.DamgleStoryReactionBox
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel

@Composable
fun DamgleStoryBoxInner(damgleStoryBoxState: DamgleStoryBoxModel) {
    Box(
        modifier = Modifier
            .height(340.dp)
            .width(328.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_damgle),
            contentDescription = null,
            modifier = Modifier
                .height(340.dp)
                .width(328.dp),
        )
        Column {
            Text(
                text = damgleStoryBoxState.content,
                fontSize = 16.sp,
                maxLines = 6,
                lineHeight = (16 * 1.55).sp,
                modifier = Modifier
                    .padding(end = 20.dp, start = 20.dp, top = 20.dp)
                    .weight(1f),
            )
            DamgleStoryReactionBox(
                modifier = Modifier,
                damgleStoryBoxState.reactions
            )
        }
    }
}
