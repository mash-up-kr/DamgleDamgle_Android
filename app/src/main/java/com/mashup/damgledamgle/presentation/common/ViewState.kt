package com.mashup.damgledamgle.presentation.common

/**
 *  ViewState.kt
 *
 *  Created by Minji Jeong on 2022/07/26
 *  Copyright © 2022 MashUp All rights reserved.
 */

sealed class ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val error: String) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
}

fun <T> ViewState<T>.successOrNull(): T? {
    return if (this is ViewState.Success<T>) return this.data else null
}
