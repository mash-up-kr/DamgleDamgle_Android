package com.mashup.damgledamgle.domain.entity.base

/**
 *  NetworkResponse.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

sealed class NetworkResponse<out T> {
    class Success<T>(val data: T) : NetworkResponse<T>()
    class Error(val exception: Exception) : NetworkResponse<Nothing>()
}
