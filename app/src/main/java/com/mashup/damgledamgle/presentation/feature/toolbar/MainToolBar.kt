package com.mashup.damgledamgle.presentation.feature.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.White
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle

@Composable
fun MainToolBar(title: String?, iconClickAction: () -> Unit,) {

    TopAppBar(
        backgroundColor = colorResource(id = R.color.damgle_default_black),
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                Alignment.Center) {
                if (title != null) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = pretendardTextStyle.bodySemibold16,
                        textAlign = TextAlign.Center,
                    )
                }
                IconButton(
                    onClick = iconClickAction,
                    modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(
                        painterResource(id = R.drawable.ic_mypage),
                        "MyPage",
                        tint = White)
                }
            }
        }
    )

}
