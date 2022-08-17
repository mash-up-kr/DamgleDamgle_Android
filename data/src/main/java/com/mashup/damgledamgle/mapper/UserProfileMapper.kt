package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.UserProfile
import com.mashup.damgledamgle.repository.spec.UserProfileResponse
import javax.inject.Inject

/**
 *  UserProfileMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class UserProfileMapper @Inject constructor() {
    fun mapToEntity(spec: UserProfileResponse): UserProfile {
        return UserProfile(
            userNo = spec.userNo,
            nickName = spec.nickName,
            notification = spec.notification
        )
    }
}
