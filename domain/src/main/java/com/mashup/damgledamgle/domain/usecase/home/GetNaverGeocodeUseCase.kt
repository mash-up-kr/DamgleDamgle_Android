package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.MapRepository
import javax.inject.Inject

class GetNaverGeocodeUseCase @Inject constructor(
    private val mapRepository: MapRepository
    ) {
    suspend operator fun invoke(coords: String) : Result<GeoResult>
        = mapRepository.getReverseGeocoding(coords)
}