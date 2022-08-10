package com.mashup.damgledamgle.domain.entity.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

fun <T> CoroutineScope.launchWithNetworkResult(
    networkResponse: NetworkResponse<T>,
    suspendOnSuccess: suspend CoroutineScope.(T) -> Unit,
    suspendOnError: suspend CoroutineScope.(Exception) -> Unit,
) {
    when(networkResponse) {
        is NetworkResponse.Success -> launch {
            suspendOnSuccess(networkResponse.data)
        }
        is NetworkResponse.Error -> launch {
            suspendOnError(networkResponse.exception)
        }
    }
}
