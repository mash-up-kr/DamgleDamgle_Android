package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.*

@Composable
fun BottomSheetExpandedContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    alpha: Float,
) {
    val openLeaveStoryDialog = remember { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }

    if (openLeaveStoryDialog.value) {
        LeaveStoryDialog(
            openLeaveStoryDialog,
            { openLeaveStoryDialog.value = false },
            {
                openLeaveStoryDialog.value = false
                navController.navigate("leave_story_screen/${text}")
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .alpha(alpha)
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                fontSize = 32.sp,
                style = pretendardTextStyle.title1Bold32,
                text = stringResource(R.string.home_bottomsheet_leave_story_on_this_place)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontSize = 15.sp,
                color = Gray900,
                style = pretendardTextStyle.bodyMedium16,
                text = stringResource(R.string.home_bottomsheet_story_left_here_disappears_after_a_month)
            )
            Spacer(modifier = Modifier.height(32.dp))
            DamgleStoryBoxWriting(textColor = Gray900, text = text, onTextChange = { text = it })
        }
        BottomSheetBottomButton(text = text) {
            openLeaveStoryDialog.value = true
        }
    }
}
