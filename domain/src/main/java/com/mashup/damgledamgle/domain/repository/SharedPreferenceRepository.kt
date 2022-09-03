package com.mashup.damgledamgle.domain.repository

/**
 *  SharedPreferenceRepository.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface SharedPreferenceRepository {
    fun getString(key: String): String
    fun setString(key: String, value: String)
}
