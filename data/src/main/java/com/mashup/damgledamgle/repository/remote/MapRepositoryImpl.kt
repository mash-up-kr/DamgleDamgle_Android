package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.MapRepository
import com.mashup.damgledamgle.mapper.geocodeMapper
import com.mashup.damgledamgle.network.DamgleApi
import com.mashup.damgledamgle.network.NaverApi
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val naverApi: NaverApi
    ) : MapRepository {

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
