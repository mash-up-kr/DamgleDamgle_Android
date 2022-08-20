package com.mashup.damgledamgle.repository.spec.response

import com.squareup.moshi.Json

/**
 *  UserResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/28
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class UserResponse(
    @Json(name = "nickname") val nickName: String,
    @Json(name = "userNo") val userNo: Int,
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String,
)
