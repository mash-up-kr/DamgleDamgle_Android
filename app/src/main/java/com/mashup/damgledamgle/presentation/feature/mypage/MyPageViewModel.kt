package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse
import com.mashup.damgledamgle.domain.usecase.user.DeleteUserProfileUseCase
import com.mashup.damgledamgle.domain.usecase.user.GetMyDamgleListUseCase
import com.mashup.damgledamgle.domain.usecase.user.GetUserProfileUserCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.mypage.model.MyDamgleModel
import com.mashup.damgledamgle.presentation.feature.mypage.model.UserProfileModel
import com.mashup.damgledamgle.presentation.feature.mypage.model.mapper.DamgleMapper
import com.mashup.damgledamgle.presentation.feature.mypage.model.mapper.UserProfileMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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
    private val getMyDamgleListUseCase: GetMyDamgleListUseCase,
    private val userProfileMapper: UserProfileMapper,
    private val damgleMapper: DamgleMapper
): ViewModel() {
    val userProfileState = MutableStateFlow<ViewState<UserProfileModel>>(ViewState.Loading)
    val myDamgleListState = MutableStateFlow<ViewState<List<MyDamgleModel>>>(ViewState.Loading)

    val uiState = userProfileState.combine(myDamgleListState) { userProfileState, myDamgleListState ->
        if (userProfileState is ViewState.Success && myDamgleListState is ViewState.Success) ViewState.Success(Unit)
        else if (userProfileState is ViewState.Error || myDamgleListState is ViewState.Success) ViewState.Error("")
        else ViewState.Loading
    }

    private val _deleteUserState = MutableStateFlow<ViewState<String>?>(null)
    val deleteUserState: StateFlow<ViewState<String>?> = _deleteUserState.asStateFlow()

    init {
        getUserProfileInfo()
        getMyDamgleList()
    }

    private fun getUserProfileInfo() {
        viewModelScope.launch {
            when (val result = getUserProfileUserCase()) {
                is NetworkResponse.Success -> userProfileState.value =
                    ViewState.Success(userProfileMapper.mapToModel(result.data))
                is NetworkResponse.Error -> userProfileState.value =
                    ViewState.Error(result.exception.message.toString())
            }
        }
    }

    private fun getMyDamgleList() {
        viewModelScope.launch {
            when (val result = getMyDamgleListUseCase()) {
                is NetworkResponse.Success -> myDamgleListState.value =
                    ViewState.Success(damgleMapper.mapToModel(result.data))
                is NetworkResponse.Error -> myDamgleListState.value = ViewState.Error(result.exception.message.toString())
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
