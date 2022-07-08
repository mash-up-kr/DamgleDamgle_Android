package com.mashup.damgledamgle.presentation.common.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.Gray400


@Composable
fun DamgleDialogOuter(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
) {
    Card(
            modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
            shape = RoundedCornerShape(14.dp),
            backgroundColor = Gray400
    ) {
        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                        .width(IntrinsicSize.Max)
        ) {
            content()
        }
    }
}
