package com.mashup.damgledamgle.presentation.feature.mypage.model

import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction

/**
 *  MyDamgleModel.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

data class MyDamgleModel(
    val id: String,
    val placeName: String,
    val dateTime: String,
    val reactions: Reaction
)
