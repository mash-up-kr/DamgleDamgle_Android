package com.mashup.damgledamgle.presentation.feature.home.damgle

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.leave_story.*
import com.mashup.damgledamgle.ui.theme.Grey500
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun DamgleClearComplete(navController : NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey500),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        val isVisible = MutableLiveData(false)
        var innerText by remember {
            mutableStateOf(R.string.damgle_clear_time)
        }
        coroutineScope.launch {
            delay(2900)
            isVisible.value = true
        }
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
            LeaveStoryInner(innerText, lottieRes = R.raw.paint)
        }
        if(isVisible.observeAsState().value == true) {
            innerText = R.string.damgle_clear_complete
            isVisible.value = true
            LeaveStoryBottomButton(text = "확인하러 가기", onButtonClick = {
                navigationToHome(navController)
            })
        }

    }
}

fun navigationToHome(navController: NavHostController) {
    navController.navigate("home_screen") {
        popUpTo("home_screen") { inclusive = true }
    }
}