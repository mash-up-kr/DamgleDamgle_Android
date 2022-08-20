package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R

@Composable
fun LeaveStoryError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            GNB(
                rightContent = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier
                            .width(24.dp)
                            .offset(x = (-16).dp)
                            .clickable {
                                // TODO 백스택 로직이 정해지면 수정
                            }
                    )
                }
            )
            Spacer(modifier = Modifier.height(28.dp))
            LeaveStoryInner(
                titleRes = R.string.leave_story_now_leaving_a_story_error,
                subscriptionRes = R.string.leave_story_now_leaving_a_story_error_description,
                // TODO 에러로 파일 교체
                lottieRes = R.raw.paint
            )
        }
        LeaveStoryBottomButton("다시 담글 남기러 가기")
    }
}

