package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.enumerate.DamgleStorySort
import com.mashup.damgledamgle.enumerate.toKorean
import com.mashup.damgledamgle.presentation.feature.leave_story.GNB
import com.mashup.damgledamgle.ui.theme.*
import com.mashup.damgledamgle.util.ToastUtil

@Composable
fun AllDamgleListScreen(navController: NavHostController, viewModel: AllDamgleListViewModel = hiltViewModel()) {
    val context = LocalContext.current

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = backgroundGradient
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GNB(
                    centerContent = {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "담벼락 전체보기"
                        )
                    },
                    rightContent = {
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
                )
                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                            DamgleStorySort.values().forEach {
                                val interactionSource = remember { MutableInteractionSource() }
                                Text(
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = null
                                        ) { viewModel.changeDamgleSortStrategy(it) },
                                    text = it.key,
                                    color = if (viewModel.damgleSortStrategy == it) Black else Gray800,
                                    fontWeight = if (viewModel.damgleSortStrategy == it) FontWeight.Bold else FontWeight.Normal,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    viewModel.damgleBoxState.forEach { boxState ->
                        item {
                            DamgleStoryBox(
                                boxState.value,
                                { viewModel.reactMain(boxState.key) },
                                { reaction ->
                                    viewModel.react(boxState.key, reaction)
                                    ToastUtil.show(context, "${reaction.toKorean()} 이모지로 수정되었어요!")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
