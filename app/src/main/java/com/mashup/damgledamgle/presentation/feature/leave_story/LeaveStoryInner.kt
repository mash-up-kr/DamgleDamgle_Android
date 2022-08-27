package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.presentation.component.common.LottieBox
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle

@Composable
fun LeaveStoryInner(@StringRes titleRes: Int, @StringRes subscriptionRes: Int? = null, @RawRes lottieRes: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            style = pretendardTextStyle.title1Bold32,
            text = stringResource(titleRes),
        )
        Box(modifier = Modifier.height(56.dp)) {
            if (subscriptionRes != null)
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = pretendardTextStyle.bodyMedium13,
                    text = stringResource(subscriptionRes),
                )
        }
        Box(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            LottieBox(lottieRes)
        }
    }
}
