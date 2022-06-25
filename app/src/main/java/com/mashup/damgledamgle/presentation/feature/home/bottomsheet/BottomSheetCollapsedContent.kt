package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

@Composable
fun BottomSheetCollapsedContent(alpha: Float) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
            modifier = Modifier.alpha(alpha),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.home_leave_my_story)
    )
}
