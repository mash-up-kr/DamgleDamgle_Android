package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOuter
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogTwoButtonInner

/**
 *  ConfirmDeleteUserDialog.kt
 *
 *  Created by Minji Jeong on 2022/08/21
 *  Copyright © 2022 MashUp All rights reserved.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmDeleteUserDialog(
    openConfirmDeleteUserDialog: MutableState<Boolean>,
    onDeleteUserButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            openConfirmDeleteUserDialog.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogTwoButtonInner(
                title = "정말 내 정보를 삭제하고\n서비스를 그만 사용하실건가요??",
                description = "모든 계정 정보를 삭제하시면 \n" +
                        "다시 되살릴 수 없어요!",
                firstButtonText = "삭제",
                firstButtonAction = onDeleteUserButtonClick,
                secondButtonText = "삭제 취소",
                secondButtonAction = onCancelButtonClick,
            )
        }
    }
}
