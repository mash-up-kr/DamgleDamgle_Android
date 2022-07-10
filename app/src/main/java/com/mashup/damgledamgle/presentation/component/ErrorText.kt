package com.mashup.damgledamgle.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.SystemRed

@Composable
fun ErrorText(
        modifier: Modifier = Modifier,
        text: String
) {
    Row(modifier = modifier) {
        Icon(
                painter = painterResource(id = R.drawable.ic_error),
                tint = SystemRed,
                contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = SystemRed)
    }
}
