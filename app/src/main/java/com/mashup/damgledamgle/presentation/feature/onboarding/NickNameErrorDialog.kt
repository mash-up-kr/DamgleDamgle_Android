package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.presentation.common.dialog.*

/**
 *  NickNameErrorDialog.kt
 *
 *  Created by Minji Jeong on 2022/08/21
 *  Copyright © 2022 MashUp All rights reserved.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NickNameErrorDialog(
    openNickNameErrorDialog: MutableState<Boolean>,
    onButtonClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            openNickNameErrorDialog.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogOneButtonInner(
                title = "닉네임 생성에 실패했어요.",
                description = "네트워크가 불안정해서 닉네임을 생성하기\n어려워요. 잠시 후에 다시 시도해주세요.",
                firstButtonText = "확인",
                firstButtonAction = onButtonClick,
            )
        }
    }
}
