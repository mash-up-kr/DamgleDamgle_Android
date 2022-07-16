package com.mashup.damgledamgle.presentation.feature.home

import androidx.lifecycle.ViewModel
import com.mashup.damgledamgle.R

class HomeViewModel : ViewModel()  {


    fun getMyLocation() {

    }

    //서버에서 받는 마커 표시 위치 ArrayList<Info>
    //Info는 아이콘, 읽음 처리, 위도 경도에 대한 정보를 담은 data class
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