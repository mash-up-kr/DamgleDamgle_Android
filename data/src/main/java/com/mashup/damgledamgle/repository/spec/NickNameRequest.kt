package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

/**
 *  NickNameRequest.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class NickNameRequest(
    @Json(name = "name") val name: String,
    @Json(name = "adjective") val adjective: String,
    @Json(name = "noun") val noun: String,
    @Json(name = "nth") val nth: Int,
)
