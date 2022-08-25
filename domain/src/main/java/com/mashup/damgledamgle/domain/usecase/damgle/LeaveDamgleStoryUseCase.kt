package com.mashup.damgledamgle.domain.usecase.damgle

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.DamgleRepository
import javax.inject.Inject

class LeaveDamgleStoryUseCase @Inject constructor(
    private val damgleRepository: DamgleRepository
) {
    suspend operator fun invoke(longitude: Double, latitude: Double, content: String, address1: String, address2: String): Result<Damgle> {
        return damgleRepository.writeDamgle(longitude, latitude, content, address1, address2)
    }
}
