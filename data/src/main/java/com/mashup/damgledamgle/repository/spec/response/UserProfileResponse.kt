package com.mashup.damgledamgle.repository.spec.response

import com.squareup.moshi.Json

/**
 *  UserProfileResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class UserProfileResponse(
    @Json(name = "nickname") val nickName: String,
    @Json(name = "userNo") val userNo: Int,
    @Json(name = "notification") val notification: Boolean,
)
