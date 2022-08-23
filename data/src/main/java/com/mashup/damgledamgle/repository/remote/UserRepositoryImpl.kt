package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.domain.entity.UserProfile
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.UserRepository
import com.mashup.damgledamgle.domain.usecase.token.SetTokenUseCase
import com.mashup.damgledamgle.mapper.UserProfileMapper
import com.mashup.damgledamgle.network.DamgleApi
import javax.inject.Inject

/**
 *  UserRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class UserRepositoryImpl @Inject constructor(
    private val damgleApi: DamgleApi,
    private val userMapper: UserProfileMapper,
    private val setTokenUseCase: SetTokenUseCase
) : UserRepository {

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
            setTokenUseCase("", "")
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
