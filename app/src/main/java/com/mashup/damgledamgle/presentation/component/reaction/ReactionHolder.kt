package com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.Gray400

@Composable
fun ReactionHolder(
    modifier: Modifier = Modifier,
    boxColor: Color,
    onClickReactionHolder: () -> Unit,
    @DrawableRes imageRes: Int
) {
    Box(
        modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onClickReactionHolder() })
            .background(boxColor)
            .padding(5.dp),
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp)
                .background(color = boxColor)
        )
    }
}
