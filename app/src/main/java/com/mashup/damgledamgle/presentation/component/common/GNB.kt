package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GNB(
    leftContent: @Composable BoxScope.() -> Unit = {},
    centerContent: @Composable BoxScope.() -> Unit = {},
    rightContent: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(66.dp)
            .fillMaxWidth(),
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            leftContent()
        }
        Box(modifier = Modifier.align(Alignment.Center)) {
            centerContent()
        }
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            rightContent()
        }
    }
}
