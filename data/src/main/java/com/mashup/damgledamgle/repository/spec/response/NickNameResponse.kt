package com.mashup.damgledamgle.repository.spec.response

import com.squareup.moshi.Json

/**
 *  NickNameResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class NickNameResponse(
    @Json(name = "name") val name: String,
    @Json(name = "adjective") val adjective: String,
    @Json(name = "noun") val noun: String,
    @Json(name = "nth") val nth: Int,
)
