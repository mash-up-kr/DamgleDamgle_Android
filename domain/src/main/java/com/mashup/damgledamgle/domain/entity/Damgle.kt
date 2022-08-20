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
    val createdAt: Long,
    val updatedAt: Long,
) {
    data class Reaction(
        val userNo: Int,
        val nickName: String,
        val type: String,
    )
}
