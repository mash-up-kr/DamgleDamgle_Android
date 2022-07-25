package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.OnboardingRepository
import com.mashup.damgledamgle.mapper.NickNameMapper
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import javax.inject.Inject

/**
 *  OnboardingRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class OnboardingRepositoryImpl @Inject constructor(
    private val nickNameMapper: NickNameMapper
) : OnboardingRepository {
    override suspend fun getNickName(adjective: String?, noun: String?): NetworkResponse<NickName> {
        val resultData = if (adjective != null) {
            ServiceBuilder.damgleApi.getNickName(adjective = adjective)
        } else if (noun != null) {
            ServiceBuilder.damgleApi.getNickName(noun = noun)
        } else {
            ServiceBuilder.damgleApi.getNickName()
        }

        return try {
            NetworkResponse.Success(nickNameMapper.mapToEntity(resultData))
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }
    }
}
