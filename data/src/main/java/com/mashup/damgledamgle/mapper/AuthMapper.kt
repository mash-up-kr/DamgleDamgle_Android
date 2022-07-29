package com.mashup.damgledamgle.mapper

import com.mashup.damgledamgle.domain.entity.User
import com.mashup.damgledamgle.repository.spec.UserResponse
import javax.inject.Inject

/**
 *  AuthMapper.kt
 *
 *  Created by Minji Jeong on 2022/07/28
 *  Copyright © 2022 MashUp All rights reserved.
 */

class AuthMapper @Inject constructor() {
    fun mapToEntity(spec: UserResponse): User {
        return User(
            nickName = spec.nickName,
            userNo = spec.userNo,
            token = spec.accessToken,
            refreshToken = spec.refreshToken
        )
    }
}
