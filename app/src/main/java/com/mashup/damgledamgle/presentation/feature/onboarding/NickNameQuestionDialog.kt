package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOuter
import com.mashup.damgledamgle.ui.theme.*

/**
 *  NickNameQuestionDialog.kt
 *
 *  Created by Minji Jeong on 2022/08/31
 *  Copyright © 2022 GwanakMT All rights reserved.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NickNameQuestionDialog(
    nth: Int,
    openNickNameErrorDialog: MutableState<Boolean>,
) {
    Dialog(
        onDismissRequest = {
            openNickNameErrorDialog.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleNickNameQuestionDialogInner(
                nth = nth,
                actionCancel = { openNickNameErrorDialog.value = false }
            )
        }
    }
}

@Composable
fun DamgleNickNameQuestionDialogInner(
    nth: Int,
    actionCancel: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(color = Grey500)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Orange500)) {
                        append(context.getString(R.string.nickname_nth_onboarding_title_nth, nth))
                    }
                    append(context.getString(R.string.nickname_nth_onboarding_title))
                },
                color = Gray1000,
                style = pretendardTextStyle.bodyBold18,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 26.sp
            )

            Image(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "닫기",
                modifier = Modifier.clickable { actionCancel() }
                    .align(Alignment.TopEnd)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = context.getString(R.string.nickname_nth_onboarding_message),
            style = pretendardTextStyle.bodyMedium16,
            color = Gray900,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
