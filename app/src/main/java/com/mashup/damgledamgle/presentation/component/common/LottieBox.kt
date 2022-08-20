package com.mashup.damgledamgle.presentation.component.common

import androidx.annotation.RawRes
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*

@Composable
fun LottieBox(@RawRes rawRes: Int) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(lottieComposition) {
        lottieAnimatable.animate(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }

    LottieAnimation(
        composition = lottieComposition,
        progress = lottieAnimatable.progress,
        contentScale = ContentScale.FillHeight
    )
}
