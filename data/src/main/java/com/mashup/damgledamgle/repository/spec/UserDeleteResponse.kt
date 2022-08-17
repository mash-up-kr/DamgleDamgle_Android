package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

/**
 *  UserDeleteResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class UserDeleteResponse(
    @Json(name = "message") val message: String
)
