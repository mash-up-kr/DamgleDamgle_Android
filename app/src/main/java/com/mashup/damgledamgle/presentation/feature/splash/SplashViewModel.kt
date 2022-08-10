package com.mashup.damgledamgle.presentation.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.usecase.token.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  SplashViewModel.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
): ViewModel() {
    private val _isUserRegistered = MutableStateFlow<Boolean?>(null)
    val isUserRegistered: StateFlow<Boolean?> = _isUserRegistered.asStateFlow()

    init {
        getIsUserRegistered()
    }

    private fun getIsUserRegistered() {
        viewModelScope.launch {
            val isRegistered = getTokenUseCase().isNotEmpty()
            _isUserRegistered.emit(isRegistered)
        }
    }
}
