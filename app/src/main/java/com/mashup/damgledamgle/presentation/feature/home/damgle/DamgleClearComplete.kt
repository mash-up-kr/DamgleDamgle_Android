package com.mashup.damgledamgle.presentation.feature.home.damgle

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.leave_story.*
import com.mashup.damgledamgle.ui.theme.Grey500

@Composable
fun DamgleClearComplete(navController : NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Grey500),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            GNB(
                rightContent = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier
                            .width(24.dp)
                            .offset(x = (-16).dp)
                            .clickable {
                                navigationToHome(navController)
                            }
                    )
                }
            )
            Spacer(modifier = Modifier.height(28.dp))
            LeaveStoryInner(R.string.damgle_clear_complete, lottieRes = R.raw.paint)
        }
        LeaveStoryBottomButton(text = "확인하러 가기", onButtonClick = {
            navigationToHome(navController)
        })
    }
}

fun navigationToHome(navController: NavHostController) {
    navController.navigate("home_screen") {
        popUpTo("home_screen") { inclusive = true }
    }
}