package com.mashup.damgledamgle.presentation.feature.home.map.marker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.lifecycle.LiveData
import com.mashup.damgledamgle.R

class MarkerCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    companion object {
        var isRead = true
        var isDuple = false
        var resId  = R.drawable.ic_angry_small
        var cnt = ""
    }

    @Composable
    override fun Content() {
        MarkerBox(isRead = isRead, isDuple = isDuple, resId,"0")
    }

    fun convertViewToBitmap(view: MarkerCustomView) : Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }

    fun init() {
        MarkerCustomView(context)
    }

}