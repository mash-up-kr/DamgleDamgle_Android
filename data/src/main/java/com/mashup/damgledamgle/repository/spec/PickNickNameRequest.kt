package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

/**
 *  PickNickNameRequest.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class PickNickNameRequest(
    @Json(name = "adjective") val adjective: String,
    @Json(name = "noun") val noun: String,
)
