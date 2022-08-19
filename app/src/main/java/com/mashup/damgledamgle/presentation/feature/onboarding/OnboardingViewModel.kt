package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.NickName
import com.mashup.damgledamgle.domain.entity.User
import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.usecase.onboarding.GetRandomNickNameUseCase
import com.mashup.damgledamgle.domain.usecase.onboarding.PickNickNameUseCase
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
    private val pickNickNameUseCase: PickNickNameUseCase,
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

    private fun setNickName(result: Result<NickName>) {
        viewModelScope.launch {
            when (result) {
                is Result.Success -> {
                    nickName.value = nickNameMapper.mapToModel(result.data)
                    _uiState.emit(ViewState.Success(null))
                }
                is Result.Error -> {
                    _uiState.emit(ViewState.Error(result.exception.message.toString()))
                }
            }
        }
    }

    fun signUp(nickName: NickNameModel, notification: Boolean) {
        viewModelScope.launch {
            when(val pickedNickNameResult = pickNickNameUseCase(nickName.adjective, nickName.noun)) {
                is Result.Success -> {
                    when(val result = signUpUseCase(pickedNickNameResult.data.name, notification)) {
                        is Result.Success -> {
                            _authState.emit(ViewState.Success(result.data))
                        }
                        is Result.Error -> {
                            _authState.emit(ViewState.Error(result.exception.message.toString()))
                        }
                    }
                }
                is Result.Error -> {
                    _authState.emit(ViewState.Error(pickedNickNameResult.exception.message.toString()))
                }
            }
        }
    }
}
