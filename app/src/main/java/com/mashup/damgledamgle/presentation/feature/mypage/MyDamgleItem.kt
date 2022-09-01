package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.commonModel.DamgleModel
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.ui.theme.*
import com.mashup.damgledamgle.util.ReactionUtil
import com.mashup.damgledamgle.util.TimeUtil

/**
 *  MyDamgleItem.kt
 *
 *  Created by Minji Jeong on 2022/07/12
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun MyDamgleItem(
    navHostController: NavHostController?,
    damgleModel: DamgleModel
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable {
                navHostController?.navigate("${Screen.SingleDamgle.route}/${damgleModel.id}")
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_mydamgle_gray_gradient),
            contentDescription = "MyDamgle Item Background"
        )

        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(102.dp)
                .matchParentSize()
                .padding(horizontal = 20.dp),
        ) {
            Row(
                verticalAlignment = CenterVertically
            ) {
                val res = when (ReactionUtil.getMainIconFromReactionSummaryList(damgleModel.reactionSummary)) {
                    "angry" -> R.drawable.ic_angry_big
                    "sad" -> R.drawable.ic_sad_big
                    "amazing" -> R.drawable.ic_amazing_big
                    "best" -> R.drawable.ic_best_big
                    "like" -> R.drawable.ic_heart_big
                    else -> R.drawable.ic_noreaction_small
                }

                MyDamgleEmoji(res, damgleModel.reactions.count(), Modifier.weight(1f, false))
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, bottom = 4.dp)
                ) {
                    damgleModel.address1?.let {
                        Text(
                            text = it,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            color = Gray900,
                            modifier = Modifier.weight(1f, false)
                        )
                    }

                    damgleModel.address2?.let {
                        Text(
                            text = it,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            color = Gray900,
                            modifier = Modifier.weight(1f, false)
                        )
                    }
                }
            }
            Text(
                text = TimeUtil.getFormattedTimeDiff(damgleModel.updatedAt),
                style = pretendardTextStyle.bodyMedium13,
                color = Orange500,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f, false),
            )
        }
    }
}

@Composable
fun MyDamgleEmoji(emojiDrawableId: Int, count: Int, modifier: Modifier) {
    val context = LocalContext.current
    val (w, h) = with(LocalDensity.current) {
        16.dp.roundToPx() to 16.dp.roundToPx()
    }
    val yellowCircleBitmap = remember {
        ContextCompat.getDrawable(context, R.drawable.background_mydamgle_emoji_count)
            ?.toBitmap(w, h)?.asImageBitmap() ?: return
    }

    Box(
        modifier = modifier.size(60.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = painterResource(id = emojiDrawableId),
            contentDescription = "MyDamgle Emoji",
            modifier = Modifier.matchParentSize()
        )

        if (count > 0)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .size(16.dp)
            ) {
                Image(
                    bitmap = yellowCircleBitmap,
                    contentDescription = "MyDamgle Emoji Count",
                )

                Text(
                    text = count.toString(),
                    style = pretendardTextStyle.bodyMedium10,
                    color = Gray1000
                )
            }
    }
}

@Preview
@Composable
fun PreviewMyDamgleItem() {
    MyDamgleItem(
        null,
        DamgleModel(
            id = "",
            userNo = "",
            nickName = "",
            x = 0.0,
            y = 0.0,
            content = "",
            reactions = listOf(),
            reactionSummary = listOf(),
            reactionOfMine = null,
            address1 = "",
            address2 = "",
            reports = listOf(),
            createdAt = 0,
            updatedAt = 0
        )
    )
}

@Preview
@Composable
fun PreviewMyDamgleEmoji() {
    MyDamgleEmoji(emojiDrawableId = R.drawable.ic_heart_big, count = 11, modifier = Modifier)
}
