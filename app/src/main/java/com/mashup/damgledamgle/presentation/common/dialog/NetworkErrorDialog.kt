package com.mashup.damgledamgle.presentation.common.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NetworkErrorDialog(
    openNetworkDialog: MutableState<Boolean>,
    onButtonClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            openNetworkDialog.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogOneButtonInner(
                title = "네트워크가 불안정해요.",
                description = "지금은 내용을 불러오기 어려워요.\n잠시 후에 다시 시도해주세요.",
                firstButtonText = "확인",
                firstButtonAction = onButtonClick,
            )
        }
    }
}
