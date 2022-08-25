package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.common.DisabledInteractionSource
import com.mashup.damgledamgle.ui.theme.LottieBackGround

@Composable
fun LoadingLottie() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.refresh_lottie)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 2000),
            initialProgress = 0f
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(LottieBackGround.copy(0.5F))
            .clickable(interactionSource = DisabledInteractionSource(), indication = null) { },
    ) {
        LottieAnimation(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit,
            composition = composition,
            progress = lottieAnimatable.progress
        )
    }

}