package com.mashup.damgledamgle.presentation.feature.home.map.marker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

@Composable
fun MarkerBox(isRead : Boolean, isDuple : Boolean, iconRes : Int, cnt : String) {
    Box {
        Box(modifier = Modifier.padding(6.dp)){
            Image(
                painter = painterResource(id = R.drawable.ic_balloon_comm),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(25.dp)
            )
            if(!isRead) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notify),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                )
            }
        }
        if(isDuple) {
            Box(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_count_background),
                    contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
                Text(text = cnt,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 8.sp)
            }
        }
    }

}