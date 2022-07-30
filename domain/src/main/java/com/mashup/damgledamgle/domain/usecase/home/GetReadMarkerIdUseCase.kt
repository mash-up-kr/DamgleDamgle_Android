package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.repository.ReadMarkerRepository
import javax.inject.Inject

class GetReadMarkerIdUseCase @Inject constructor(
    private val readMarkerRepository: ReadMarkerRepository
    ) {
    suspend fun invoke(id : Int) : Boolean = readMarkerRepository.getReadMarkerId(id)
}