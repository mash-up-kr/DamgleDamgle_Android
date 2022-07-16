package com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.Black

@Composable
fun ReactionHolder(
        modifier: Modifier = Modifier,
        onClickReactionHolder: () -> Unit,
        @DrawableRes imageRes: Int
) {
    Card(
            modifier
                    .size(40.dp)
                    .background(color = Black, shape = RoundedCornerShape(8.dp))
                    .clickable(
                            onClick = { onClickReactionHolder() },
                    )
                    .padding(5.dp),
    ) {
        Image(
                painter = painterResource(imageRes),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                        .size(30.dp)
                        .background(color = Black)
        )
    }
}
