package com.mashup.damgledamgle.presentation.feature.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

@Composable
fun MainToolBar(title: String?, action: () -> Unit,) {

    var location by remember {
        mutableStateOf(title)
    }
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                Alignment.Center) {
                if (title != null) {
                    location?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                IconButton(
                    onClick = action,
                    modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(painterResource(id = R.drawable.ic_mypage),"MyPage")
                }
            }
        },
        backgroundColor = colorResource(id = R.color.damgle_default_black)
    )

}
