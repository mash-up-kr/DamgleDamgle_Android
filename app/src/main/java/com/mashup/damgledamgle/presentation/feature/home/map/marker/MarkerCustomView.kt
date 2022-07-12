package com.mashup.damgledamgle.presentation.feature.home.map.marker

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
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

}