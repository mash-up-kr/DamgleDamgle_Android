package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.White

@Composable
fun LeaveStoryBottomButton(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Black)
    ) {
        Text(
            text = text,
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
