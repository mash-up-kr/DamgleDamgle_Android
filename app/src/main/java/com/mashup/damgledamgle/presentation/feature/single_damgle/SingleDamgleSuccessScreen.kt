package com.mashup.damgledamgle.presentation.feature.single_damgle

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.enumerate.toKorean
import com.mashup.damgledamgle.presentation.component.story_box.DamgleStoryBox
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel
import com.mashup.damgledamgle.presentation.feature.leave_story.GNB
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle
import com.mashup.damgledamgle.util.ToastUtil

@Composable
fun SingleDamgleSuccessScreen(
    navController: NavController,
    viewModel: SingleDamgleViewModel,
    damgle: DamgleStoryBoxModel
) {
    val context = LocalContext.current

    Column {
        GNB(
            centerContent = {
                Text(
                    text = "내 담글 확인하기",
                    style = pretendardTextStyle.bodyMedium16,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp),
                    textAlign = TextAlign.Center
                )
            },
            rightContent = {
                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .width(24.dp)
                        .offset(x = (-16).dp)
                        .padding(top = 28.dp)
                        .clickable { navController.popBackStack() }
                )
            }
        )
        DamgleStoryBox(
            damgle,
            { viewModel.extendReactionBox() },
            { reaction ->
                if (reaction == damgle.myReaction) {
                    ToastUtil.show(context, "이모지가 취소되었어요!")
                    viewModel.deleteReaction(damgle.id)
                } else {
                    ToastUtil.show(context, "${reaction.toKorean()} 이모지로 수정되었어요!")
                    viewModel.reactDamgle(damgle.id, reaction)
                }
            },
            isMine = damgle.isMine
        )
    }
}
