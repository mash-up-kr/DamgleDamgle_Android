package com.mashup.damgledamgle.repository.spec.response

import com.squareup.moshi.Json

/**
 *  NickNameRequest.kt
 *
 *  Created by Minji Jeong on 2022/07/28
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class NickNameRequest(
    @Json(name = "nickname") val nickName: String,
    @Json(name = "notification") val notification: Boolean
)
