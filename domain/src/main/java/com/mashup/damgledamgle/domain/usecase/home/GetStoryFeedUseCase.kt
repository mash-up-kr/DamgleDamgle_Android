package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.MapRepository
import javax.inject.Inject

class GetStoryFeedUseCase @Inject constructor(
    private val mapRepository: MapRepository
    ) {
    suspend fun invoke(
        top : Double,
        bottom : Double,
        left : Double,
        right : Double
    ) : Result<List<Damgle>>
        = mapRepository.getStoryFeedList(
        top = top,
        bottom = bottom,
        left = left,
        right = right
    )
}