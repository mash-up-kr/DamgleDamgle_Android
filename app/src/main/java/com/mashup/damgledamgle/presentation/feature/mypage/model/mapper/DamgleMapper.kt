package com.mashup.damgledamgle.presentation.feature.mypage.model.mapper

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.mypage.model.MyDamgleModel
import javax.inject.Inject

/**
 *  DamgleMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 GwanakMT All rights reserved.
 */

class DamgleMapper @Inject constructor() {
    fun mapToModel(entity: List<Damgle>): List<MyDamgleModel> {
        return entity.map {
            MyDamgleModel(
                id = it.id,
                placeName = "GANGNAMGU\nYEOKSAMDONG",
                dateTime = "5분전",
                reactions = Reaction.LIKE
            )
        }
    }
}
