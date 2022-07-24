package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.presentation.feature.mypage.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  MyPageViewModel.kt
 *
 *  Created by Minji Jeong on 2022/07/08
 *  Copyright © 2022 MashUp All rights reserved.
 */

@HiltViewModel
class MyPageViewModel @Inject constructor(): ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    init {
        getUserProfileInfo()
    }

    private fun getUserProfileInfo() {
        viewModelScope.launch {
            // TODO(minji): 앱 내 영역에 사용자 아이디 및 닉네임 저장하기
            _userProfile.emit(UserProfile("user id", "솜사탕씻은 너구리")) // 임시데이터
        }
    }
}
