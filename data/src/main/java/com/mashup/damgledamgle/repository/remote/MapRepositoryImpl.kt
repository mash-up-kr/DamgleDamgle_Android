package com.mashup.damgledamgle.repository.remote

import android.content.Context
import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.MapRepository
import com.mashup.damgledamgle.mapper.DamgleMapper
import com.mashup.damgledamgle.mapper.geocodeMapper
import com.mashup.damgledamgle.network.DamgleApi
import com.mashup.damgledamgle.network.NaverApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val naverApi: NaverApi,
    private val damgleApi: DamgleApi,
    private val damgleMapper: DamgleMapper,
    @ApplicationContext private val context: Context
) : MapRepository {

    private val prefs =
        context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    override fun getLastEntryDamgleDay(): String {
        return prefs.getString(LAST_ENTRY_DAY, "") ?: ""
    }

    override fun setLastEntryDamgleDay(date: String) {
        prefs.edit().putString(LAST_ENTRY_DAY, date).apply()
    }

    override suspend fun getReverseGeocoding(
        coors: String
    ): Result<GeoResult> {
        return try {
            val result = naverApi.getReverseGeocoding(
                BuildConfig.NAVER_CLIENT_ID,
                BuildConfig.NAVER_CLIENT_KEY,
                coors,
                "roadaddr",
                "json"
            )
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
    ): Result<List<Damgle>> {
        return try {
            val storyFeedResult = damgleApi.getStoryFeed(
                top = top,
                bottom = bottom,
                left = left,
                right = right
            )
            Result.Success(storyFeedResult.stories.map {
                damgleMapper.mapToEntity(it)
            }// TODO 임시로직
                .filter { !it.address1.isNullOrEmpty() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    companion object {
        private const val PREFERENCE_FILE_NAME = "DamgleDamgle"
        private const val LAST_ENTRY_DAY = "LAST_ENTRY_DAMGLE_DAY"
    }
}
