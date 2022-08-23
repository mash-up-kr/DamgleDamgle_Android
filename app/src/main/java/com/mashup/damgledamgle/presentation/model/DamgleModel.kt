package com.mashup.damgledamgle.presentation.model

/**
 *  DamgleModel.kt
 *
 *  Created by Minji Jeong on 2022/08/20
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class DamgleModel(
    val id: String,
    val userNo: String,
    val nickName: String,
    val x: Double,
    val y: Double,
    val content: String,
    val reactions: List<DamgleReactionModel>,
    val createdAt: Long,
    val updatedAt: Long,
)
