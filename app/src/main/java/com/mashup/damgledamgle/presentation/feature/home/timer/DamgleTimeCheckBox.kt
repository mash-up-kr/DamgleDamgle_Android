package com.mashup.damgledamgle.presentation.feature.home.timer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

@Composable
fun DamgleTimeCheckBox(restTime : String, oneDay : Boolean) {

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = if(oneDay)
            colorResource(id = R.color.damgle_main_orange) else {
            colorResource(id = R.color.damgle_default_black)
        }
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
                fontSize = 12.sp,
                color = Color.White,

            )
            Text(
                text = restTime,
                fontSize = 12.sp,
                color = colorResource(id = R.color.damgle_light_green)
            )
        }
    }
}