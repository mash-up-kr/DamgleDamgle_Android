package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.Gray1000
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle
import com.mashup.damgledamgle.util.noRippleClickable

@Composable
fun BottomSheetCollapsedContent(
    modifier: Modifier = Modifier,
    alpha: Float,
    onClickBottomSheet: () -> Unit
) {
    Box(modifier = Modifier
        .noRippleClickable {
            onClickBottomSheet()
        }
        .fillMaxWidth()
        .height(68.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .alpha(alpha)
                .align(Alignment.Center),
            color = Gray1000,
            style = pretendardTextStyle.bodyBold18,
            text = stringResource(R.string.home_bottomsheet_leave_my_story)
        )
    }
}
