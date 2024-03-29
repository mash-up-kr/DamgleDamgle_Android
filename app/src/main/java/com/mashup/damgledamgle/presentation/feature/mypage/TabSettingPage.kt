package com.mashup.damgledamgle.presentation.feature.mypage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.ui.theme.*

/**
 *  TabSettingPage.kt
 *
 *  Created by Minji Jeong on 2022/06/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun TabSettingPage(
    navController: NavHostController?,
    notification: Boolean
) {
    val context = LocalContext.current
    val viewModel: MyPageViewModel = hiltViewModel()
    val openErrorDialog = remember { mutableStateOf(false) }
    val openConfirmDeleteUserDialog = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.background_horizontal_orange_gradient),
                    contentDescription = "Orange Gradient Background",
                )

                PushSettingItem(notification) {
                    viewModel.patchNotificationAllowState()
                }
            }

            Text(
                text = context.getString(R.string.mypage_delete_user),
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .clickable { openConfirmDeleteUserDialog.value = true },
                style = pretendardTextStyle.bodyMedium13,
                color = Gray600
            )
        }

        if (viewModel.deleteUserState.collectAsState().value is ViewState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Orange500
            )
        }

        LaunchedEffect(key1 = viewModel.deleteUserState.collectAsState().value) {
            when (val result = viewModel.deleteUserState.value) {
                is ViewState.Success -> {
                    Toast.makeText(context, "${result.data}", Toast.LENGTH_SHORT).show()
                    navController?.navigate(Screen.Onboarding.route)
                }
                is ViewState.Error -> openErrorDialog.value = true
            }
        }
    }

    if (openErrorDialog.value) {
        DeleteUserErrorDialog(
            openDeleteUserErrorDialog = openErrorDialog,
            onButtonClick = { openErrorDialog.value = false }
        )
    }

    if (openConfirmDeleteUserDialog.value) {
        ConfirmDeleteUserDialog(
            openConfirmDeleteUserDialog = openConfirmDeleteUserDialog,
            onDeleteUserButtonClick = {
                openConfirmDeleteUserDialog.value = false
                viewModel.deleteUser()
            },
            onCancelButtonClick = {
                openConfirmDeleteUserDialog.value = false
            }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewTabSettingPage() {
    TabSettingPage(null, true)
}
