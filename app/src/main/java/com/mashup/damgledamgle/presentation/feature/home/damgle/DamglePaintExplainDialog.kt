package com.mashup.damgledamgle.presentation.feature.home.damgle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOneButtonInner
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOuter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DamglePaintExplainDialog(
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
                title = stringResource(id = R.string.damgle_paint_explain_title),
                description = stringResource(id = R.string.damgle_paint_explain_content),
                firstButtonText = "확인",
                firstButtonAction = onConfirmButtonClick,
            )
        }
    }
}