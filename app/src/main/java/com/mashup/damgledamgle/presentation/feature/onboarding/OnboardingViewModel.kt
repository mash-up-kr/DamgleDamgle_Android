package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.onboarding.GetRandomNickNameUseCase
import com.mashup.damgledamgle.presentation.feature.onboarding.model.NickNameModel
import com.mashup.damgledamgle.presentation.feature.onboarding.model.mapper.NickNameMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
) : ViewModel() {
    private val _nickName = MutableStateFlow(NickNameModel())
    val nickName: StateFlow<NickNameModel> = _nickName

    init {
        getNickName()
    }

    fun getNickName() {
        viewModelScope.launch {
            when(val result = getRandomNickNameUseCase()) {
                is NetworkResponse.Success -> {
                    _nickName.value = nickNameMapper.mapToModel(result.data)
                }
                is NetworkResponse.Error -> {

                }
            }
        }
    }

    fun refreshNickName(nickName: NickNameModel) {
        viewModelScope.launch {
            if (nickName.adjective.isEmpty()) {
                // TODO: 형용사 다시 조회
            } else if (nickName.noun.isEmpty()) {
                // TODO: 명사 다시 조회
            }
        }
    }
}
