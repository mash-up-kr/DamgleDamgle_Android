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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.domain.entity.EnglishAddress
import com.mashup.damgledamgle.presentation.feature.mypage.model.DamgleModel
import com.mashup.damgledamgle.ui.theme.Orange500
import com.mashup.damgledamgle.ui.theme.akzidenzGroteskTextStyle
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle

/**
 *  MyDamgleItem.kt
 *
 *  Created by Minji Jeong on 2022/07/12
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun MyDamgleItem(
    damgle: DamgleModel
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable { }
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
            Row {
                MyDamgleEmoji(R.drawable.ic_heart_big, 11, Modifier.weight(1f, false))
                Column(
                    modifier = Modifier
                        .align(CenterVertically)
                        .weight(2f, false)
                        .padding(bottom = 4.dp, start = 12.dp)
                ) {
                    Text(
                        text = damgle.engAddress.sggName,
                        style = akzidenzGroteskTextStyle.title2Bold18,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.height(21.dp),
                    )
                    Text(
                        text = damgle.engAddress.roadName,
                        style = akzidenzGroteskTextStyle.title2Bold18,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.height(21.dp),
                    )
                }
            }

            Text(
                text = "5분 전",
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

            Text(text = count.toString(), style = TextStyle(fontSize = 10.sp))
        }
    }
}

@Preview
@Composable
fun PreviewMyDamgleItem() {
    MyDamgleItem(
        DamgleModel(
            id = "",
            userNo = "",
            nickName = "",
            x = "",
            y = "",
            engAddress = EnglishAddress("GANGNAMGU HANADULSET", "YEOKSAMDONG"),
            content = "",
            reactions = listOf(),
            createAt = 0,
            updateAt = 0
        )
    )
}

@Preview
@Composable
fun PreviewMyDamgleEmoji() {
    MyDamgleEmoji(emojiDrawableId = R.drawable.ic_heart_big, count = 11, modifier = Modifier)
}
