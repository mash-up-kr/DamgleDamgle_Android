package com.mashup.damgledamgle.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.ui.theme.Gray1000
import com.mashup.damgledamgle.ui.theme.Yellow

@Preview
@Composable
fun ReactionCountCircleDemo() {
    Row {
        ReactionCountCircle(count = 0)
        ReactionCountCircle(count = 1)
        ReactionCountCircle(count = 99)
        ReactionCountCircle(count = 10000)
    }
}

@Composable
fun ReactionCountCircle(modifier: Modifier = Modifier, count: Int) {
    if (count == 0) return
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Yellow)
            .border(
                width = 1.dp,
                color = Gray1000,
                shape = CircleShape
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = if (count > 99) "99+" else count.toString(),
            fontSize = 11.sp
        )
    }
}
