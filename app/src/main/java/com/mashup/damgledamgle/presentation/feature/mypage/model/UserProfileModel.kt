package com.mashup.damgledamgle.presentation.feature.mypage.model

/**
 *  UserProfileModel.kt
 *
 *  Created by Minji Jeong on 2022/07/08
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class UserProfileModel(
    val userNo: Int,
    val nickName: String,
    val notification: Boolean,
)
