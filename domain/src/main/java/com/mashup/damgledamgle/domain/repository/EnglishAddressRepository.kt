package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.EnglishAddress
import com.mashup.damgledamgle.domain.entity.base.Result

/**
 *  EnglishAddressRepository.kt
 *
 *  Created by Minji Jeong on 2022/08/14
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface EnglishAddressRepository {
    suspend fun getEnglishAddress(address: String): Result<EnglishAddress>
}
