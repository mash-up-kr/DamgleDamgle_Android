package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.User
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.onboarding.GetRandomNickNameUseCase
import com.mashup.damgledamgle.domain.usecase.onboarding.SignUpUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.onboarding.model.NickNameModel
import com.mashup.damgledamgle.presentation.feature.onboarding.model.mapper.NickNameMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  OnboardingViewModel.kt
 *
 *  Created by Minji Jeong on 2022/07/24
 *  Copyright © 2022 MashUp All rights reserved.
 */

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getRandomNickNameUseCase: GetRandomNickNameUseCase,
    private val nickNameMapper: NickNameMapper,
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ViewState<*>>(ViewState.Loading)
    val uiState: StateFlow<ViewState<*>> = _uiState.asStateFlow()

    private val _authState = MutableStateFlow<ViewState<User>>(ViewState.Loading)
    val authState: StateFlow<ViewState<User>> = _authState.asStateFlow()

    val nickName = mutableStateOf(NickNameModel())

    init {
        getNickName()
    }

    private fun getNickName() {
        viewModelScope.launch {
            _uiState.emit(ViewState.Loading)
            setNickName(getRandomNickNameUseCase())
        }
    }

    fun refreshNickNameNoun(adjective: String) {
        viewModelScope.launch {
            _uiState.emit(ViewState.Loading)
            setNickName(getRandomNickNameUseCase(adjective = adjective))
        }
    }

    fun refreshNickNameAdjective(noun: String) {
        viewModelScope.launch {
            _uiState.emit(ViewState.Loading)
            setNickName(getRandomNickNameUseCase(noun = noun))
        }
    }

    private fun setNickName(result: NetworkResponse<NickName>) {
        when (result) {
            is NetworkResponse.Success -> {
                nickName.value = nickNameMapper.mapToModel(result.data)
                _uiState.value = ViewState.Success(null)
            }
            is NetworkResponse.Error -> {
                _uiState.value = ViewState.Error(result.exception.message.toString())
            }
        }
    }

    fun signUp(nickName: String) {
        viewModelScope.launch {
            when(val result = signUpUseCase.invoke(nickName)) {
                is NetworkResponse.Success -> _authState.value = ViewState.Success(result.data)
                is NetworkResponse.Error -> _authState.value = ViewState.Error(result.exception.message.toString())
            }
        }
    }
}
