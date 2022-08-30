package com.mashup.damgledamgle.presentation.common.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.BackgroundDim


@Composable
fun DamgleDialogOuter(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
) {
    Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            backgroundColor = BackgroundDim
    ) {
        content()
    }
}
