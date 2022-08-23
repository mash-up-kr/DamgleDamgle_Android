package com.mashup.damgledamgle.model

import com.mashup.damgledamgle.domain.entity.Damgle

/**
 *  DamgleModel.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class DamgleModel(
    val id: String,
    val userNo: String,
    val nickName: String,
    val x: Double,
    val y: Double,
    val content: String,
    val reactions: List<Reaction>,
    val reactionSummary: List<ReactionSummary>,
    val reactionOfMine: ReactionOfMine?,
    val reports: List<Reports>,
    val createdAt: Long,
    val updatedAt: Long,
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
        val userNo : Int,
        val nickname : String,
        val type : String
    )

    data class Reports(
        val userNo : Int,
        val createdAt : Long
    )
}


