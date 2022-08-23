package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogOuter
import com.mashup.damgledamgle.presentation.common.dialog.DamgleDialogTwoButtonInner

/**
 *  SettingPermissionDialog.kt
 *
 *  Created by Minji Jeong on 2022/08/20
 *  Copyright © 2022 MashUp All rights reserved.
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingPermissionDialog(
    openSettingPermissionDialogState: MutableState<Boolean>,
    onMoveToSettingButton: () -> Unit,
    onKillAppButton: () -> Unit,
) {

    Dialog(
        onDismissRequest = {
            openSettingPermissionDialogState.value = false
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DamgleDialogOuter {
            DamgleDialogTwoButtonInner(
                title = "워치 권한이 필요해요",
                description = "위치 권한에 동의해야 앱을 사용할 수 있어요\n설정 페이지로 이동한 후 위치 권한을 허용해주세요!",
                firstButtonText = "설정하러 가기",
                firstButtonAction = onMoveToSettingButton,
                secondButtonText = "앱 종료",
                secondButtonAction = onKillAppButton,
            )
        }
    }
}
