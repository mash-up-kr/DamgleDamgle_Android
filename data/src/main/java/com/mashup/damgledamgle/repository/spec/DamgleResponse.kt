package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

/**
 *  DamgleResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class DamgleResponse(
    @Json(name = "id") val id: String,
    @Json(name = "userNo") val userNo: String,
    @Json(name = "nickName") val nickName: String,
    @Json(name = "x") val x: String,
    @Json(name = "y") val y: String,
    @Json(name = "content") val content: String,
    @Json(name = "reactions") val reactions: List<ReactionResponse>,
    @Json(name = "createAt") val createAt: Int,
    @Json(name = "updateAt") val updateAt: Int,
) {
    data class ReactionResponse(
        @Json(name = "userNo") val userNo: Int,
        @Json(name = "nickName") val nickName: String,
        @Json(name = "type") val type: String,
    )
}
