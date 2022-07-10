package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.Grey500

@Composable
fun LeaveStoryScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .background(color = Grey500)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(118.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.leave_story_now_leaving_a_story)
            )
        }
    }
}