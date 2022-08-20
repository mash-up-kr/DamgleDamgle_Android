package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.entity.StoryEntity
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.MapRepository
import javax.inject.Inject

class GetStoryFeedUseCase @Inject constructor(
    private val mapRepository: MapRepository
    ) {
    suspend fun invoke() : Result<List<StoryEntity>>
        = mapRepository.getStoryFeedList(
        top = 37.51,
        bottom = 37.49,
        left = 127.035,
        right = 127.036
    )
}