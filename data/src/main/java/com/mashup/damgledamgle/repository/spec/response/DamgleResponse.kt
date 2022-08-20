package com.mashup.damgledamgle.repository.spec.response

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
    @Json(name = "nickname") val nickName: String,
    @Json(name = "x") val x: Double,
    @Json(name = "y") val y: Double,
    @Json(name = "content") val content: String,
    @Json(name = "reactions") val reactions: List<ReactionResponse>,
    @Json(name = "reactionSummary") val reactionSummary : List<ReactionSummary>,
    @Json(name = "reactionOfMine") val reactionOfMine : ReactionOfMine?,
    @Json(name = "reports") val reports : List<Reports> ,
    @Json(name = "createdAt") val createdAt: Long,
    @Json(name = "updatedAt") val updatedAt: Long,
) {
    data class ReactionResponse(
        @Json(name = "userNo") val userNo: Int,
        @Json(name = "nickname") val nickName: String,
        @Json(name = "type") val type: String,
    )

    data class ReactionSummary(
        @Json(name = "type") val type : String,
        @Json(name = "count") val count : Int
    )

    data class ReactionOfMine(
        @Json(name = "userNo") val userNo : Int,
        @Json(name = "nickname") val nickname : String,
        @Json(name = "type") val type : String
    )

    data class Reports(
        @Json(name = "userNo") val userNo : Int,
        @Json(name = "createdAt") val createdAt : Long
    )
}