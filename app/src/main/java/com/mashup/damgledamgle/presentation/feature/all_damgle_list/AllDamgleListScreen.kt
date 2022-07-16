package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.ui.theme.Grey500

@Composable
fun AllDamgleListScreen(navController: NavHostController, viewModel: AllDamgleListViewModel = hiltViewModel()) {
    Scaffold {
        Box(
                modifier = Modifier
                        .fillMaxSize()
                        .background(Grey500)
        ) {
            Column {
                viewModel.reactionBoxState.forEach { boxState ->
                    ReactionBox(
                            boxState.value,
                            { viewModel.reactMain(boxState.key) },
                            { reaction -> viewModel.react(boxState.key, reaction) }
                    )
                }
            }
        }
    }
}
