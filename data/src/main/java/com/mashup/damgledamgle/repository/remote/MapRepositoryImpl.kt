package com.mashup.damgledamgle.repository.remote

import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.NaverGeocode
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.MapRepository
import com.mashup.damgledamgle.mapper.geocodeMapper
import com.mashup.damgledamgle.repository.network.NaverApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder) : MapRepository {

    private val naverApi by lazy { serviceBuilder.naverBuildService<NaverApi>() }

    override suspend fun getReverseGeocoding(
        coords: String): NetworkResponse<GeoResult> {
        return try {
            val result = naverApi.getReverseGeocoding(
                BuildConfig.NAVER_CLIENT_ID,
                BuildConfig.NAVER_CLIENT_KEY,
                "127.0674448,37.5472073",
                "roadaddr",
                "json")
            NetworkResponse.Success(geocodeMapper(result))
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }
    }
}