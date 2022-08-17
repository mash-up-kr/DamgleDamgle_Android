package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.domain.entity.UserProfile
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.UserRepository
import com.mashup.damgledamgle.mapper.UserProfileMapper
import com.mashup.damgledamgle.repository.network.DamgleApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import javax.inject.Inject

/**
 *  UserRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class UserRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder,
    private val userMapper: UserProfileMapper,
): UserRepository {

    private val damgleApi by lazy { serviceBuilder.buildService<DamgleApi>() }

    override suspend fun getUserProfile(): Result<UserProfile> {
        return try {
            val resultData = damgleApi.getUserProfile()
            Result.Success(userMapper.mapToEntity(resultData))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteUserProfile(): Result<String> {
        return try {
            val resultData = damgleApi.deleteMe()
            Result.Success(resultData.message)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun switchNotification(): Result<Boolean> {
        return try {
            val resultData = damgleApi.switchNotification()
            Result.Success(resultData.notification)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
