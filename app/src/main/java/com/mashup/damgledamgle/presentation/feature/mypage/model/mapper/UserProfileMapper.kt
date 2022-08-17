package com.mashup.damgledamgle.presentation.feature.mypage.model.mapper

import com.mashup.damgledamgle.domain.entity.UserProfile
import com.mashup.damgledamgle.presentation.feature.mypage.model.UserProfileModel
import javax.inject.Inject

/**
 *  UserProfileMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class UserProfileMapper @Inject constructor() {
    fun mapToModel(entity: UserProfile): UserProfileModel {
        return UserProfileModel(
            userNo = entity.userNo,
            nickName = entity.nickName,
            notification = entity.notification
        )
    }
}
