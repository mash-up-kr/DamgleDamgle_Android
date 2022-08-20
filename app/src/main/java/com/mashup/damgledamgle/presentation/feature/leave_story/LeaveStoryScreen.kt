package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.ui.theme.Grey500

@Composable
fun LeaveStoryScreen(navController: NavHostController, content: String, leaveStoryViewModel: LeaveStoryViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        // TODO 위치 가져오는 로직 추가 필요
        leaveStoryViewModel.leaveStory(32.0, 32.0, content)
    }

    val leaveStoryState by leaveStoryViewModel.leaveDamgleStoryState.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .background(color = Grey500)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val state = leaveStoryState) {
                is ViewState.Loading -> LeaveStoryWriting()
                is ViewState.Error -> state.error//LeaveStoryError()
                is ViewState.Success -> LeaveStoryComplete(state.data)
            }
        }
    }
}
