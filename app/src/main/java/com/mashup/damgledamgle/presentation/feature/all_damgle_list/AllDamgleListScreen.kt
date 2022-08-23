package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.ViewState

@Composable
fun AllDamgleListScreen(navController: NavHostController, viewModel: AllDamgleListViewModel = hiltViewModel()) {
    val damgleList by viewModel.damgleFeedState.collectAsState(ViewState.Loading)

    LaunchedEffect(Unit) {
        viewModel.init(37.516486063, 37.476486063, 127.008361548, 127.048361548)
    }

    when (val state = damgleList) {
        is ViewState.Success -> AllDamgleListSuccessScreen(navController = navController, viewModel = viewModel, damgleList = state.data)
        is ViewState.Loading -> {}
        is ViewState.Error -> {}
    }
}
