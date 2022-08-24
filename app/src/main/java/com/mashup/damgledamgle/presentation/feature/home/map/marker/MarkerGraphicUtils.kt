package com.mashup.damgledamgle.presentation.feature.home.map.marker

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.feature.home.model.GroupMarkerInfo


@SuppressLint("InflateParams")
fun makeCustomMarkerView(markerInfo: GroupMarkerInfo, context: Context) = (
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.item_marker,null) as ConstraintLayout).apply {

    val balloonRes =
        if(markerInfo.isMine) R.drawable.ic_text_balloon_me
        else R.drawable.ic_text_balloon

    val res = when(markerInfo.mainIcon) {
        "angry" -> R.drawable.ic_angry_small
        "sad" -> R.drawable.ic_sad_small
        "amazing" -> R.drawable.ic_amazing_small
        "best" -> R.drawable.ic_best_small
        "like" -> R.drawable.ic_heart_small
        else -> R.drawable.ic_noreaction_small
    }

    (this.getViewById(R.id.marker_icon) as ImageView).setImageResource(res)
    (this.getViewById(R.id.balloon_background) as ImageView).setImageResource(balloonRes)

    if(markerInfo.count <= 1) {
        (this.getViewById(R.id.count_text) as TextView).visibility = View.GONE
        (this.getViewById(R.id.count_icon) as ImageView).visibility = View.GONE
    }
    else {
        (this.getViewById(R.id.count_icon) as ImageView).visibility = View.VISIBLE
        (this.getViewById(R.id.count_text) as TextView).visibility = View.VISIBLE
        (this.getViewById(R.id.count_text) as TextView).text =
            if(markerInfo.count > 99) "99+" else markerInfo.count.toString()
    }
}