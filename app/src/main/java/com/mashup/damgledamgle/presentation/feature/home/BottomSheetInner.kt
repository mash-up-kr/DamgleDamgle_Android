package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

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
            Text(
                    modifier = Modifier
                            .padding(top = 20.dp)
                            .alpha(getBottomSheetSlide(bottomSheetScaffoldState.bottomSheetState)),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.home_leave_my_story)
            )
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
