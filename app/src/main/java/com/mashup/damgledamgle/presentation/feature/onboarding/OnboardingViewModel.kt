package com.mashup.damgledamgle.presentation.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.presentation.feature.onboarding.model.NickName
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

) : ViewModel() {
    private val _nickName = MutableStateFlow(NickName())
    val nickName: StateFlow<NickName> = _nickName

    fun getNickName() {
        viewModelScope.launch {
            _nickName.value = NickName(
                fullName = "잠자는 첫번째 햄스터",
                adjective = "잠자는",
                noun = "햄스터",
                nth = 1
            )
        }
    }

    fun refreshNickName(nickName: NickName) {
        viewModelScope.launch {
            if (nickName.adjective.isEmpty()) {
                // TODO: 형용사 다시 조회
            } else if (nickName.noun.isEmpty()) {
                // TODO: 명사 다시 조회
            }
        }
    }
}
