package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
    ) {
        Column(
                modifier = Modifier
                        .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                    modifier = Modifier
                            .padding(top = 16.dp)
                            .width(64.dp),
                    color = Color.DarkGray,
                    thickness = 2.dp
            )
            val alpha = getBottomSheetSlide(bottomSheetScaffoldState.bottomSheetState)
            if (alpha >= 0.7f) {
                BottomSheetCollapsedContent(alpha = alpha)
            } else {
                BottomSheetExpandedContent(alpha = 1 - alpha)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun getBottomSheetSlide(state: BottomSheetState): Float {
    val fraction = state.progress.fraction
    return when {
        state.currentValue == state.targetValue -> {
            if (state.currentValue == BottomSheetValue.Collapsed) 1f else 0f
        }
        state.isCollapsed -> 1 - fraction
        else -> fraction
    }
}
