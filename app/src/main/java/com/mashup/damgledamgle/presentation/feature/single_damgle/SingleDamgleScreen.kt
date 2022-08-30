package com.mashup.damgledamgle.presentation.feature.single_damgle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.ui.theme.Grey500
import com.mashup.damgledamgle.ui.theme.backgroundGradient

/**
 *  SingleDamgleScreen.kt
 *
 *  Created by Minji Jeong on 2022/08/23
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun SingleDamgleScreen(navController: NavHostController, id: String, viewModel: SingleDamgleViewModel = hiltViewModel()) {
    Surface(
        color = Grey500,
        modifier = Modifier.fillMaxSize(),
    ) {
        val damgle by viewModel.damgle.collectAsState(ViewState.Loading)

        LaunchedEffect(Unit) {
            viewModel.init(id)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = backgroundGradient
                )
        ) {
            when (val state = damgle) {
                is ViewState.Success -> SingleDamgleSuccessScreen(
                    navController = navController,
                    viewModel = viewModel,
                    damgle = state.data
                )
                is ViewState.Loading -> {}
                is ViewState.Error -> Text(text = state.error)
            }
        }
    }
}
