package com.mashup.damgledamgle.domain.usecase.geo

import com.mashup.damgledamgle.domain.entity.EnglishAddress
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.EnglishAddressRepository
import javax.inject.Inject

/**
 *  ConvertAddressToEnglishUseCase.kt
 *
 *  Created by Minji Jeong on 2022/08/14
 *  Copyright © 2022 MashUp All rights reserved.
 */

class ConvertAddressToEnglishUseCase @Inject constructor(
    private val englishAddressRepository: EnglishAddressRepository
) {
    suspend operator fun invoke(x: String, y: String): EnglishAddress {
        val address = "성동구 아차산로13길" // FIXME: feature/map 브랜치랑 합쳐지면 위도경도로 주소 찾아서 넣기

        return when(val result = englishAddressRepository.getEnglishAddress(address)) {
            is Result.Success -> EnglishAddress(result.data.sggName, result.data.roadName)
            is Result.Error -> EnglishAddress("", "") // TODO: 어떻게 처리할까
        }
    }
}
