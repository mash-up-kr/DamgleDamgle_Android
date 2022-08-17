package com.mashup.damgledamgle.util

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun show(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
