package com.mashup.damgledamgle.presentation.feature.home.map

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
fun FloatingButton(fabIcon : ImageBitmap, description : String) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }) {
                Icon(bitmap = fabIcon, description)
            }
        }
    ) {

    }
}

@Composable
fun FloatingButtonSetting() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {

    }
}

