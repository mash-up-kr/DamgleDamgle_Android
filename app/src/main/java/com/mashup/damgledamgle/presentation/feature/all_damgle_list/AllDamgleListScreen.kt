package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.home.model.Bound

@Composable
fun AllDamgleListScreen(navController: NavHostController, bound: Bound, viewModel: AllDamgleListViewModel = hiltViewModel()) {
    val damgleList by viewModel.damgleFeedState.collectAsState(ViewState.Loading)

    LaunchedEffect(Unit) {
        viewModel.init(
            top = bound.top,
            bottom = bound.bottom,
            left = bound.left,
            right = bound.right
        )
    }

    when (val state = damgleList) {
        is ViewState.Success -> AllDamgleListSuccessScreen(navController = navController, viewModel = viewModel, damgleList = state.data)
        is ViewState.Loading -> {}
        is ViewState.Error -> {}
    }
}
