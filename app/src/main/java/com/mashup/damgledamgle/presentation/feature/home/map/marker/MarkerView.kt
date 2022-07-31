package com.mashup.damgledamgle.presentation.feature.home.map.marker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.mashup.damgledamgle.R

class MarkerView(context: Context) : View(context) {


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {


    }
}

@SuppressLint("ViewConstructor")
class MarkerCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    @Composable
    override fun Content() {
        MarkerBox(isRead = false, isDuple = false, R.drawable.ic_heart_small, "98")
    }


}
