package com.mashup.damgledamgle.domain.entity

/**
 *  Damgle.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class Damgle(
    val id: String,
    val userNo: String,
    val nickName: String,
    val x: Double,
    val y: Double,
    val content: String,
    val reactions: List<Reaction>,
    val reactionSummary : List<ReactionSummary>,
    val reactionOfMine : ReactionOfMine,
    val reports : List<Reports>,
    val createAt: Long,
    val updateAt: Long,
) {
    data class Reaction(
        val userNo: Int,
        val nickName: String,
        val type: String,
    )

    data class ReactionSummary(
        val type : String,
        val count : Int
    )

    data class ReactionOfMine(
        val type : String,
        val count : Int
    )

    data class Reports(
        val userNo : Int,
        val createdAt : Long
    )
}

