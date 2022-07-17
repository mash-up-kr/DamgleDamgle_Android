package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mashup.damgledamgle.presentation.common.BackPressInterceptor
import com.mashup.damgledamgle.ui.theme.akzidenzGroteskTextStyle
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle

/**
 *  OnboardingScreen.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current

    BackPressInterceptor(context)

    // TODO: conflict나면 지워주세요. (텍스트 스타일 테스트용으로 추가해둔 텍스트들입니다)
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "TITLE-BOLD32",
            style = akzidenzGroteskTextStyle.title1Bold32,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "TITLE2-BOLD18",
            style = akzidenzGroteskTextStyle.title2Bold18,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "BODY1-BODY20",
            style = akzidenzGroteskTextStyle.body1Bold20,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "BODY2-BOLD13",
            style = akzidenzGroteskTextStyle.body2Bold13,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Divider()

        Text(
            text = "Title1-bold32",
            style = pretendardTextStyle.title1Bold32,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "body_medium24",
            style = pretendardTextStyle.bodyMedium24,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "body_bold18",
            style = pretendardTextStyle.bodyBold18,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "body_medium18",
            style = pretendardTextStyle.bodyMedium18,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "body_semibold16",
            style = pretendardTextStyle.bodySemibold16,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = "body_medium16",
            style = pretendardTextStyle.bodyMedium16,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = "body_bold13",
            style = pretendardTextStyle.bodyBold13,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = "body_medium13",
            style = pretendardTextStyle.bodyMedium13,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = "body_semibold11",
            style = pretendardTextStyle.bodySemibold11,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = "body_semibold10",
            style = pretendardTextStyle.bodySemibold10,
            modifier = Modifier.padding(8.dp),
        )
    }
}
