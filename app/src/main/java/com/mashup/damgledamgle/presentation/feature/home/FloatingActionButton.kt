package com.mashup.damgledamgle.presentation.feature.home

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.mashup.damgledamgle.R

@Composable
fun FloatingActionButton(
    fabIcon : Int,
    description : String,
    modifier: Modifier,
    onClick : () -> Unit) {

    FloatingActionButton(
        modifier = modifier,
        backgroundColor = colorResource(id = R.color.damgle_default_black),
        onClick = onClick
    ) {
        Icon(
            painterResource(id = fabIcon),
            description,
            tint = Color.White
        )
    }
}
