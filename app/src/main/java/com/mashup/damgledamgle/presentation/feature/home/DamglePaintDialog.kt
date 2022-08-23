package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.presentation.common.dialog.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DamglePaintDialog(
    date : String,
    openDamglePainDialog: MutableState<Boolean>,
    onConfirmButtonClick: () -> Unit,
){
    Dialog(
        onDismissRequest = {
            openDamglePainDialog.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogOneButtonInner(
                title = "$date 새롭게 담벼락을 칠했어요.",
                description = "아래 확인 버튼을 눌러서 \n" +
                        "새롭게 시작주세요.",
                firstButtonText = "확인",
                firstButtonAction = onConfirmButtonClick,
            )
        }
    }
}