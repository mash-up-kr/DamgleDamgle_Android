package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.User
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.OnboardingRepository
import com.mashup.damgledamgle.mapper.AuthMapper
import com.mashup.damgledamgle.mapper.NickNameMapper
import com.mashup.damgledamgle.repository.network.DamgleApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import com.mashup.damgledamgle.repository.spec.response.NickNameRequest
import com.mashup.damgledamgle.repository.spec.response.PickNickNameRequest
import retrofit2.HttpException
import javax.inject.Inject

/**
 *  OnboardingRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class OnboardingRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder,
    private val nickNameMapper: NickNameMapper,
    private val authMapper: AuthMapper,
) : OnboardingRepository {

    private val damgleApi by lazy { serviceBuilder.buildService<DamgleApi>() }

    override suspend fun getNickName(adjective: String?, noun: String?): Result<NickName> {
        return try {
            val resultData = when {
                adjective != null -> damgleApi.getNickName(adjective = adjective)
                noun != null -> damgleApi.getNickName(noun = noun)
                else -> damgleApi.getNickName()
            }

            Result.Success(nickNameMapper.mapToEntity(resultData))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun pickNickName(adjective: String, noun: String): Result<NickName> {
        return try {
            val resultData = damgleApi.pickNickName(PickNickNameRequest(adjective, noun))
            Result.Success(nickNameMapper.mapToEntity(resultData))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun signUp(nickName: String, notification: Boolean): Result<User> {
        return try {
            val resultData = damgleApi.signUp(NickNameRequest(nickName, notification))
            Result.Success(authMapper.mapToEntity(resultData))
        } catch (e: Exception) {
            if ((e as HttpException).code() == 400) {
                Result.Error(Exception("닉네임이 중복되었습니다: $nickName"))
            } else {
                Result.Error(e)
            }
        }
    }
}
