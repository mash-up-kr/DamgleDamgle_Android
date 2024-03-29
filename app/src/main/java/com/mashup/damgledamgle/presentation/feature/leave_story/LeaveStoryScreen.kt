package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.navigation.Screen
import com.mashup.damgledamgle.ui.theme.Grey500
import com.mashup.damgledamgle.util.LocationUtil

@Composable
fun LeaveStoryScreen(navController: NavHostController, content: String, leaveStoryViewModel: LeaveStoryViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        LocationUtil.getMyLocation(context)?.let {
            leaveStoryViewModel.leaveStory(it.latitude, it.longitude, content)
        }
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
                is ViewState.Error -> LeaveStoryError {
                    navController.navigate(Screen.Home.route)
                }
                is ViewState.Success -> LeaveStoryComplete(
                    state.data,
                    { damgle ->
                        navController.popBackStack()
                        navController.currentBackStackEntry?.savedStateHandle?.set("id", damgle.id)
                        navController.navigate("${Screen.SingleDamgle.route}/${damgle.id}")
                    },
                    { navController.popBackStack() }
                )
            }
        }
    }
}
