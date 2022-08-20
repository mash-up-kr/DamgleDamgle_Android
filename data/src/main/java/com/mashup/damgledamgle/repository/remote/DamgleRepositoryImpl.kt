package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.DamgleRepository
import com.mashup.damgledamgle.mapper.DamgleMapper
import com.mashup.damgledamgle.repository.network.DamgleApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import com.mashup.damgledamgle.repository.spec.request.WriteDamgleRequest
import javax.inject.Inject

/**
 *  DamgleRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/31
 *  Copyright © 2022 MashUp All rights reserved.
 */

class DamgleRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder,
    private val damgleMapper: DamgleMapper,
) : DamgleRepository {

    private val damgleApi by lazy { serviceBuilder.buildService<DamgleApi>() }

    override suspend fun writeDamgle(longitude: Double, latitude: Double, content: String): Result<Damgle> {
        return try {
            val result = damgleApi.writeDamgle(WriteDamgleRequest(longitude, latitude, content))
            Result.Success(damgleMapper.mapToEntity(result))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getMyDamgleList(): Result<List<Damgle>> {
        return try {
            val resultData = damgleApi.getMyDamgleList()
            Result.Success(resultData.stories.map { damgleMapper.mapToEntity(it) })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
