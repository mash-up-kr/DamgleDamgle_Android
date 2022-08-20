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
    if (text.isEmpty()) {
        Button(
            onClick = {},
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Gray600)
        ) {
            Text(
                text = stringResource(id = R.string.home_bottomsheet_leave_a_story),
                fontSize = 18.sp,
                color = Gray400
            )
        }
    } else {
        Button(
            onClick = {
                onClickBottomSheetButton()
            },
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Black)
        ) {
            Text(
                text = stringResource(id = R.string.home_bottomsheet_leave_a_story),
                fontSize = 18.sp,
                color = Gray400
            )
        }
    }
}
