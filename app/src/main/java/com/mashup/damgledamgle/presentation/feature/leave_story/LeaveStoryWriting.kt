package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R

@Composable
fun LeaveStoryWriting() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GNB()
        Spacer(modifier = Modifier.height(28.dp))
        LeaveStoryInner(titleRes = R.string.leave_story_now_leaving_a_story, lottieRes = R.raw.write)
    }
}
