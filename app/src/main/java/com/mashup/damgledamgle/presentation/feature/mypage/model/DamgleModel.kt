package com.mashup.damgledamgle.presentation.feature.mypage.model

/**
 *  DamgleModel.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

// TODO: mypage 패키지 말고 다른 곳으로 옮기기
data class DamgleModel(
    val id: String,
    val userNo: String,
    val nickName: String,
    val x: String,
    val y: String,
    val content: String,
    val reactions: List<ReactionModel>,
    val createAt: Int,
    val updateAt: Int,
) {
    data class ReactionModel(
        val userNo: Int,
        val nickName: String,
        val type: String,
    )
}
