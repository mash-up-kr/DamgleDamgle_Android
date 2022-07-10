package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOuter
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogTwoButtonInner

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LeaveStoryDialog(
        openLeaveStoryDialog: MutableState<Boolean>,
        onRecheckButtonClick: () -> Unit,
        onLeaveStoryButtonClick: () -> Unit,
) {
    Row(
            modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
    ) {}
    Dialog(
            onDismissRequest = {
                openLeaveStoryDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogTwoButtonInner(
                    title = "이야기를 이대로 남기시겠어요?",
                    description = "이번달 말에 담벼락이 지워지기 전까지 \n" +
                            "이야기를 수정 · 삭제할 수 없어요!",
                    firstButtonText = "다시 확인하기",
                    firstButtonAction = onRecheckButtonClick,
                    secondButtonText = "이대로 남기기",
                    secondButtonAction = onLeaveStoryButtonClick,
            )
        }
    }
}
