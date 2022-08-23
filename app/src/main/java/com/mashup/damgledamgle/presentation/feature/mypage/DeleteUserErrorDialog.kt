package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOneButtonInner
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOuter

/**
 *  DeleteUserErrorDialog.kt
 *
 *  Created by Minji Jeong on 2022/08/21
 *  Copyright © 2022 MashUp All rights reserved.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeleteUserErrorDialog(
    openDeleteUserErrorDialog: MutableState<Boolean>,
    onButtonClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            openDeleteUserErrorDialog.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogOneButtonInner(
                title = "회원탈퇴 실패",
                description = "현재 네트워크 문제로 서비스 그만두기가 불가해요.\n나중에 다시 시도해주세요.",
                firstButtonText = "확인",
                firstButtonAction = onButtonClick,
            )
        }
    }
}
