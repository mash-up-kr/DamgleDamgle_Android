package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.*

@Composable
fun AllDamgleListScreen(navController: NavHostController, viewModel: AllDamgleListViewModel = hiltViewModel()) {
    Scaffold {
        Box(
                modifier = Modifier
                        .fillMaxSize()
                        .background(
                                brush = Brush.verticalGradient(
                                        0.15f to Grey500,
                                        0.3f to Orange400,
                                        0.38f to Orange600,
                                        0.7f to Orange600,
                                        0.76f to Orange400,
                                        0.85f to Grey500,
                                )
                        )
        ) {
            Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                        modifier = Modifier
                                .height(66.dp)
                                .fillMaxWidth(),
                ) {
                    Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "담벼락 전체보기"
                    )
                    Image(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = null,
                            modifier = Modifier
                                    .width(24.dp)
                                    .align(Alignment.CenterEnd)
                                    .offset(x = (-16).dp)
                                    .clickable { navController.popBackStack() }
                    )
                }
                LazyColumn {
                    item {
                        Row {
                            Text(text = "최신순")
                            Text(text = "인기순")
                        }
                    }
                    viewModel.reactionBoxState.forEach { boxState ->
                        item {
                            DamgleStoryBox(
                                    boxState.value,
                                    { viewModel.reactMain(boxState.key) },
                                    { reaction -> viewModel.react(boxState.key, reaction) }
                            )
                        }
                    }
                }
            }
        }
    }
}
