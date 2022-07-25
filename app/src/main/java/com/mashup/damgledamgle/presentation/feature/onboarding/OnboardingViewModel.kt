package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.NickName
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

    private fun getNickName() {
        viewModelScope.launch {
            setNickName(getRandomNickNameUseCase())
        }
    }

    fun refreshNickName(nickName: NickNameModel) {
        viewModelScope.launch {
            if (nickName.adjective.isEmpty()) {
                setNickName(getRandomNickNameUseCase(noun = nickName.noun))
            } else if (nickName.noun.isEmpty()) {
                setNickName(getRandomNickNameUseCase(adjective = nickName.adjective))
            }
        }
    }

    private fun setNickName(result: NetworkResponse<NickName>) {
        when(result) {
            is NetworkResponse.Success -> {
                _nickName.value = nickNameMapper.mapToModel(result.data)
            }
            is NetworkResponse.Error -> {

            }
        }
    }
}
