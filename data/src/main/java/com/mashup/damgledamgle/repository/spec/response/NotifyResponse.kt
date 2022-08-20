package com.mashup.damgledamgle.repository.spec.response

import com.squareup.moshi.Json

/**
 *  NotifyResponse.kt
 *
 *  Created by Minji Jeong on 2022/08/02
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class NotifyResponse(
    @Json(name = "nickname") val nickName: String,
    @Json(name = "userNo") val userNo: Int,
    @Json(name = "notification") val notification: Boolean
)
