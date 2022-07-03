package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

@Composable
fun BottomSheetExpandedContent(
        modifier: Modifier = Modifier,
        alpha: Float,
) {
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
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.home_leave_story_on_this_place)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                    fontSize = 13.sp,
                    text = stringResource(R.string.home_story_lefted_here_disappears_after_a_month)
            )
            Spacer(modifier = Modifier.height(32.dp))
            PutStoryBox(backGroundColor = Color.White, textColor = Color.Black)
        }

        Button(
                onClick = {},
                modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth(),
        ) {
            Text(text = "글 남기기")
        }
    }
}
