package com.mashup.damgledamgle.repository.remote

import android.util.Log
import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.domain.repository.MapRepository
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.mapper.StoryFeedMapper
import com.mashup.damgledamgle.mapper.geocodeMapper
import com.mashup.damgledamgle.repository.network.DamgleApi
import com.mashup.damgledamgle.repository.network.NaverApi
import com.mashup.damgledamgle.repository.network.ServiceBuilder
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val serviceBuilder: ServiceBuilder,
    private val storyFeedMapper: StoryFeedMapper,
) : MapRepository {

    private val damgleApi by lazy { serviceBuilder.buildService<DamgleApi>() }
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

    override suspend fun getStoryFeedList(
        top: Double,
        bottom: Double,
        left: Double,
        right: Double
    ): Result<List<StoryEntity>> {
        return try {
            val storyFeedResult = damgleApi.getStoryFeed(
                top = top,
                bottom = bottom,
                left = left,
                right = right
            )
            Result.Success(storyFeedMapper.storyFeedMapper(storyFeedResult.stories))
        } catch (e : Exception) {
            Log.d("storyFeedError", e.message.toString())
            Result.Error(e)
        }
    }
}