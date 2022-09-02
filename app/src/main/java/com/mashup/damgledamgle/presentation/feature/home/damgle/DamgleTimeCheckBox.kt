package com.mashup.damgledamgle.presentation.feature.home.damgle

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.ui.theme.pretendardTextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DamgleTimeCheckBox(
    restTime: String,
    oneHourCheck: Boolean,
    onClickEvent : () -> Unit
) {
    Card(
        modifier = Modifier.clickable{},
        shape = RoundedCornerShape(8.dp),
        backgroundColor = if(oneHourCheck)
            colorResource(id = R.color.damgle_main_orange) else {
            colorResource(id = R.color.damgle_default_black)
        },
        onClick = onClickEvent
    ) {
        Row(
            Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painterResource(id = R.drawable.ic_paint),
                contentDescription = "paint_icon",
                Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )
            Text(
                text = stringResource(id = R.string.home_map_paint_text),
                Modifier.padding(end = 8.dp),
                style = pretendardTextStyle.bodyMedium13,
                color = Color.White,

            )
            Text(
                text = restTime,
                style = pretendardTextStyle.bodyBold13,
                color = colorResource(id = R.color.damgle_light_green)
            )
        }
    }
}
