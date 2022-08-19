package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.repository.MapRepository
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.mapper.geocodeMapper
import com.mashup.damgledamgle.repository.network.NaverApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder
    ) : MapRepository {

    private val naverApi by lazy { serviceBuilder.naverBuildService<NaverApi>() }

    override suspend fun getReverseGeocoding(
        coors: String): Result<GeoResult> {
        return try {
            val result = naverApi.getReverseGeocoding(
                BuildConfig.NAVER_CLIENT_ID,
                BuildConfig.NAVER_CLIENT_KEY,
                coors,
                "roadaddr",
                "json")
            Result.Success(geocodeMapper(result))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}