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
    val x: String,
    val y: String,
    val content: String,
    val reactions: List<Reaction>,
    val createAt: Int,
    val updateAt: Int,
) {
    data class Reaction(
        val userNo: Int,
        val nickName: String,
        val type: String,
    )
}
