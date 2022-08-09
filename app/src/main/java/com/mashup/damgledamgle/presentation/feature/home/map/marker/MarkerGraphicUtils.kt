package com.mashup.damgledamgle.presentation.feature.home.map.marker

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mashup.damgledamgle.R


@SuppressLint("InflateParams")
fun makeCustomMarkerView(markerInfo: MarkerInfo, context: Context) = (
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.item_marker,null) as ConstraintLayout).apply {

    val balloonRes =
        if(markerInfo.isMine) R.drawable.ic_text_balloon_me
        else R.drawable.ic_text_balloon

    (this.getViewById(R.id.marker_icon) as ImageView).setImageResource(markerInfo.resId)
    (this.getViewById(R.id.balloon_background) as ImageView).setImageResource(balloonRes)

    if(!markerInfo.isRead && markerInfo.size <= 1) {
        (this.getViewById(R.id.count_text) as TextView).visibility = View.GONE
        (this.getViewById(R.id.count_icon) as ImageView).visibility = View.GONE
        (this.getViewById(R.id.notify_icon) as ImageView).visibility = View.VISIBLE
    }
    else if(markerInfo.size > 1) {
        (this.getViewById(R.id.notify_icon) as ImageView).visibility = View.GONE
        (this.getViewById(R.id.count_icon) as ImageView).visibility = View.VISIBLE
        (this.getViewById(R.id.count_text) as TextView).visibility = View.VISIBLE
        (this.getViewById(R.id.count_text) as TextView).text =
            if(markerInfo.size > 99) "99+" else markerInfo.size.toString()
    }
}