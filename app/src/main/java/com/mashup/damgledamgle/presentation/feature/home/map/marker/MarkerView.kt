package com.mashup.damgledamgle.presentation.feature.home.map.marker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R

@SuppressLint("ViewConstructor")
@ExperimentalMaterialApi
class MarkerView(context : Context,  bitmapCreated: (bitmap: Bitmap) -> Unit) : LinearLayoutCompat(context) {

    val res = mutableStateOf<Int>(0)

    init {
        val width = 200
        val height = 200

        val view = ComposeView(context)
        view.visibility = View.GONE
        view.layoutParams = LayoutParams(width, height)
        this.addView(view)

        view.setContent {
            MarkerBox(false, false,res.value , "0")
        }

        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view = view, width = width, height = height)
                bitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

}


