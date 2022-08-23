package com.mashup.damgledamgle.domain.usecase.home

import com.mashup.damgledamgle.domain.repository.MapRepository
import javax.inject.Inject

class SetLastEntryDamgleDayUseCase @Inject constructor(
    private val mapRepository : MapRepository
){
    operator fun invoke(date : String){
        return mapRepository.setLastEntryDamgleDay(date)
    }
}
