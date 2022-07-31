package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.entity.GeoResult
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.repository.MapRepository
import com.mashup.damgledamgle.domain.repository.ReadMarkerRepository
import javax.inject.Inject

class GetNaverGeocodeUseCase @Inject constructor(
    private val mapRepository: MapRepository
    ) {
    suspend fun invoke(coords: String) : NetworkResponse<GeoResult>
        = mapRepository.getReverseGeocoding(coords)
}