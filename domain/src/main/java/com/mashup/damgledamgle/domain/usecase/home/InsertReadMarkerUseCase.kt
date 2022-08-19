package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.entity.ReadMarkerEntity
import com.mashup.damgledamgle.domain.repository.ReadMarkerRepository
import javax.inject.Inject

class InsertReadMarkerIdUseCase @Inject constructor(
    private val readMarkerRepository: ReadMarkerRepository
) {
    suspend fun invoke(readMarkerEntity: ReadMarkerEntity) {
        readMarkerRepository.addReadMarkerId(readMarkerEntity)
    }
}