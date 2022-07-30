package com.mashup.damgledamgle.domain.entity

/**
 *  UserProfile.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class UserProfile(
    val userNo: Int,
    val nickName: String,
    val notification: Boolean,
)
