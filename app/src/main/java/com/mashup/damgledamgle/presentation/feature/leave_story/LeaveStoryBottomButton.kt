package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.White

@Composable
fun LeaveStoryBottomButton(text: String, onButtonClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Black),
        shape = CutCornerShape(0)
    ) {
        Text(
            text = text,
            color = White,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
