package com.mashup.damgledamgle.presentation.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.launchWithNetworkResult
import com.mashup.damgledamgle.domain.usecase.user.DeleteUserProfileUseCase
import com.mashup.damgledamgle.domain.usecase.user.GetMyDamgleListUserCase
import com.mashup.damgledamgle.domain.usecase.user.GetUserProfileUserCase
import com.mashup.damgledamgle.domain.usecase.user.PatchNotificationAllowStateUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.feature.mypage.model.DamgleModel
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
    private val getMyDamgleListUseCase: GetMyDamgleListUserCase,
    private val deleteUserProfileUseCase: DeleteUserProfileUseCase,
    private val patchNotificationAllowStateUseCase: PatchNotificationAllowStateUseCase,
    private val userProfileMapper: UserProfileMapper,
    private val damgleMapper: DamgleMapper,
): ViewModel() {
    val myDamgleListState = MutableStateFlow<ViewState<List<DamgleModel>>>(ViewState.Loading)
    val userProfileState = MutableStateFlow<ViewState<UserProfileModel>>(ViewState.Loading)

    private val _deleteUserState = MutableStateFlow<ViewState<String>?>(null)
    val deleteUserState: StateFlow<ViewState<String>?> = _deleteUserState.asStateFlow()

    val uiState =
        myDamgleListState.combine(userProfileState) { myDamgleListState, userProfileState ->
            if (myDamgleListState is ViewState.Success && userProfileState is ViewState.Success) {
                ViewState.Success(Unit)
            } else if (myDamgleListState is ViewState.Error || userProfileState is ViewState.Error) {
                ViewState.Error("")
            } else {
                ViewState.Loading
            }
        }


    init {
        getUserProfileInfo()
        getMyDamgleList()
    }

    private fun getUserProfileInfo() {
        viewModelScope.launch {
            launchWithNetworkResult(
                networkResponse = getUserProfileUserCase(),
                suspendOnSuccess = {
                    userProfileState.emit(ViewState.Success(userProfileMapper.mapToModel(it)))
                },
                suspendOnError = {
                    userProfileState.emit(ViewState.Error(it.message.toString()))
                }
            )
        }
    }

    private fun getMyDamgleList() {
        viewModelScope.launch {
            launchWithNetworkResult(
                networkResponse = getMyDamgleListUseCase(),
                suspendOnSuccess = {
                    myDamgleListState.emit(ViewState.Success(damgleMapper.mapToModel(it)))
                },
                suspendOnError = {
                    myDamgleListState.emit(ViewState.Error(it.message.toString()))
                }
            )
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            _deleteUserState.emit(ViewState.Loading)
            launchWithNetworkResult(
                networkResponse = deleteUserProfileUseCase(),
                suspendOnSuccess = { _deleteUserState.emit(ViewState.Success("회원 탈퇴 성공!")) },
                suspendOnError = { _deleteUserState.emit(ViewState.Error("회원 탈퇴 실패..")) }
            )
        }
    }

    fun patchNotificationAllowState() {
        viewModelScope.launch {
            patchNotificationAllowStateUseCase()
        }
    }
}
