package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.*

@Composable
fun BottomSheetBottomButton(text: String, onClickBottomSheetButton: () -> Unit) {
    // TODO 글자수에 따라 Clickable, 디자인 변경
    Button(
        onClick = {
            if (text.isNotEmpty()) onClickBottomSheetButton()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (text.isNotEmpty()) Black else Gray600),
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.home_bottomsheet_leave_a_story),
            style = pretendardTextStyle.bodyMedium18,
            color = if (text.isNotEmpty()) White else Gray400
        )
    }
}
