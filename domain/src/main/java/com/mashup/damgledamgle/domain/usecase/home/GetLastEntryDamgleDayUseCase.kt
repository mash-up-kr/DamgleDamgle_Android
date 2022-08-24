package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.repository.MapRepository
import javax.inject.Inject

class GetLastEntryDamgleDayUseCase @Inject constructor(
    private val mapRepository : MapRepository
){
     operator fun invoke(): String {
        return mapRepository.getLastEntryDamgleDay()
    }
}
