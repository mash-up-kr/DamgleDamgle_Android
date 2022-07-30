package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.user.DeleteUserProfileUseCase
import com.mashup.damgledamgle.domain.usecase.user.GetUserProfileUserCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.mypage.model.UserProfileModel
import com.mashup.damgledamgle.presentation.feature.mypage.model.mapper.UserProfileMapper
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
class MyPageViewModel @Inject constructor(
    private val getUserProfileUserCase: GetUserProfileUserCase,
    private val deleteUserProfileUseCase: DeleteUserProfileUseCase,
    private val userProfileMapper: UserProfileMapper,
): ViewModel() {
    private val _userProfileState = MutableStateFlow<ViewState<UserProfileModel>>(ViewState.Loading)
    val userProfileState: StateFlow<ViewState<UserProfileModel>> = _userProfileState.asStateFlow()

    private val _deleteUserState = MutableStateFlow<ViewState<String>?>(null)
    val deleteUserState: StateFlow<ViewState<String>?> = _deleteUserState.asStateFlow()

    init {
        getUserProfileInfo()
    }

    private fun getUserProfileInfo() {
        viewModelScope.launch {
            when(val result = getUserProfileUserCase()) {
                is NetworkResponse.Success -> _userProfileState.emit(ViewState.Success(userProfileMapper.mapToModel(result.data)))
                is NetworkResponse.Error -> _userProfileState.emit(ViewState.Error(result.exception.message.toString()))
            }
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            _deleteUserState.emit(ViewState.Loading)

            when(deleteUserProfileUseCase()) {
                is NetworkResponse.Success -> { _deleteUserState.emit(ViewState.Success("회원 탈퇴 성공!")) }
                is NetworkResponse.Error -> { _deleteUserState.emit(ViewState.Error("회원 탈퇴 실패..")) }
            }
        }
    }
}
