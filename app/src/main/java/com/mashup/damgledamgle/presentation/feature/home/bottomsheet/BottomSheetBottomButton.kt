package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.*

@Composable
fun BottomSheetBottomButton(text: String, onClickBottomSheetButton: () -> Unit) {
    Box(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable { if (text.isNotEmpty()) onClickBottomSheetButton() }
            .background(
                color = if (text.isNotEmpty()) Black else Gray600
            ),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.home_bottomsheet_leave_a_story),
            style = pretendardTextStyle.bodyMedium18,
            color = if (text.isNotEmpty()) White else Gray400
        )
    }
}
