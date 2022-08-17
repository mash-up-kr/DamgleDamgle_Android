package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

/**
 *  DamgleListResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class DamgleListResponse(
    @Json(name = "size") val size: Int,
    @Json(name = "stories") val stories: List<DamgleResponse>
)
