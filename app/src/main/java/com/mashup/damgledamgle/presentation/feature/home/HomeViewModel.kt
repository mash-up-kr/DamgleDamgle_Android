package com.mashup.damgledamgle.presentation.feature.home

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.repository.datastore.database.AppDatabase

class HomeViewModel : ViewModel()  {

    fun getMakerList() : ArrayList<MakerInfo> {
        val list : ArrayList<MakerInfo> = arrayListOf()

        list.add(MakerInfo(R.drawable.ic_heart_small,false, 37.5455113, 127.0657011))
        list.add(MakerInfo(R.drawable.ic_angry_small,true, 37.5448118, 127.0649041))
        list.add(MakerInfo(R.drawable.ic_amazing_small,false, 37.54529, 127.06630))

        return list
    }
}

data class MakerInfo(
    val resId: Int,
    val isRead: Boolean,
    val latitude: Double,
    val longitude: Double
)