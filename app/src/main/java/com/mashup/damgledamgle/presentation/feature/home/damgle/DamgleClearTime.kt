package com.mashup.damgledamgle.presentation.feature.home.damgle

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.leave_story.GNB
import com.mashup.damgledamgle.presentation.feature.leave_story.LeaveStoryInner
import com.mashup.damgledamgle.ui.theme.Grey500
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DamgleClearTime(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Grey500)
    ) {
        GNB()
        Spacer(modifier = Modifier.height(28.dp))
        LeaveStoryInner(
            titleRes = R.string.damgle_clear_time,
            lottieRes = R.raw.write
        )
    }

    coroutineScope.launch {
        delay(3500)
        navController.navigate("damgle_clear_complete_screen") {
            popUpTo("damgle_clear_time_screen") { inclusive = true }
        }
    }
}