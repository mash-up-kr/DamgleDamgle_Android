package com.mashup.damgledamgle.domain.entity

/**
 *  User.kt
 *
 *  Created by Minji Jeong on 2022/07/28
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class User(
    val nickName: String,
    val userNo: Int,
    val token: String,
    val refreshToken: String,
)
