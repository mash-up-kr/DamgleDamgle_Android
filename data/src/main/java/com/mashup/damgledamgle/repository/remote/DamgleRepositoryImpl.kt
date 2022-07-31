package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.DamgleRepository
import com.mashup.damgledamgle.mapper.DamgleMapper
import com.mashup.damgledamgle.repository.network.DamgleApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import javax.inject.Inject

/**
 *  DamgleRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 GwanakMT All rights reserved.
 */

class DamgleRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder,
    private val damgleMapper: DamgleMapper,
): DamgleRepository {

    private val damgleApi by lazy { serviceBuilder.buildService<DamgleApi>() }

    override suspend fun getMyDamgleList(): NetworkResponse<List<Damgle>> {
        return try {
            val resultData = damgleApi.getMyDamgleList()
            NetworkResponse.Success(resultData.stories.map { damgleMapper.mapToEntity(it) })
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }
    }
}