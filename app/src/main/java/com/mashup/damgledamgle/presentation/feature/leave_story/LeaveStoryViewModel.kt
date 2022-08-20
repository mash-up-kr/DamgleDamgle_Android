package com.mashup.damgledamgle.presentation.feature.leave_story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.launchWithNetworkResult
import com.mashup.damgledamgle.domain.usecase.damgle.LeaveDamgleStoryUseCase
import com.mashup.damgledamgle.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaveStoryViewModel @Inject constructor(
    private val leaveDamgleStoryRequestUseCase: LeaveDamgleStoryUseCase
) : ViewModel() {

    private val _leaveDamgleStoryState = MutableStateFlow<ViewState<Damgle>>(ViewState.Loading)
    val leaveDamgleStoryState: StateFlow<ViewState<Damgle>> = _leaveDamgleStoryState.asStateFlow()

    fun leaveStory(latitude: Double, longitude: Double, content: String) {
        viewModelScope.launch {
            delay(1500L)
            launchWithNetworkResult(
                leaveDamgleStoryRequestUseCase(longitude, latitude, content),
                { _leaveDamgleStoryState.emit(ViewState.Success(it)) },
                { _leaveDamgleStoryState.emit(ViewState.Error(it.toString())) }
            )
        }
    }
}
