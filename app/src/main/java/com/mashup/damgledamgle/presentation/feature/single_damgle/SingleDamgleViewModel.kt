package com.mashup.damgledamgle.presentation.feature.single_damgle

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.launchWithResult
import com.mashup.damgledamgle.domain.usecase.damgle.*
import com.mashup.damgledamgle.enumerate.*
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.common.successOrNull
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.ReactionBoxState
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxMapper
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class SingleDamgleViewModel @Inject constructor(
    private val getDamgleStoryUseCase: GetDamgleStoryUseCase,
    private val createDamgleReactionUseCase: CreateDamgleReactionUseCase,
    private val deleteDamgleReactionUseCase: DeleteDamgleReactionUseCase,
) : ViewModel() {

    private val _damgle = MutableStateFlow<ViewState<DamgleStoryBoxModel>>(ViewState.Loading)
    val damgle: StateFlow<ViewState<DamgleStoryBoxModel>> = _damgle.asStateFlow()

    fun init(damgleId: String) {
        viewModelScope.launch {
            launchWithResult(
                getDamgleStoryUseCase(damgleId),
                { damgle ->
                    _damgle.emit(
                        ViewState.Success(
                            DamgleStoryBoxMapper.mapToModel(
                                damgle,
                                ReactionBoxState(damgle.reactionOfMine?.type?.toReaction(), false)
                            )
                        )
                    )
                },
                { error -> _damgle.emit(ViewState.Error(error.toString())) }
            )
        }
    }

    fun extendReactionBox() {
        viewModelScope.launch {
            _damgle.value.successOrNull()?.let {
                _damgle.emit(
                    ViewState.Success(it.copy(reactionBoxState = it.reactionBoxState.copy(isExtended = true)))
                )
            }
        }
    }

    fun reactDamgle(id: String, reaction: Reaction) {
        viewModelScope.launch {
            launchWithResult(
                createDamgleReactionUseCase(storyId = id, reaction = reaction.toEnglish()),
                { damgle ->
                    _damgle.emit(
                        ViewState.Success(
                            DamgleStoryBoxMapper.mapToModel(
                                damgle,
                                ReactionBoxState(reaction, false)
                            )
                        )
                    )
                },
                { error -> _damgle.emit(ViewState.Error(error.toString())) }
            )
        }
    }

    fun deleteReaction(id: String) {
        viewModelScope.launch {
            launchWithResult(
                deleteDamgleReactionUseCase(storyId = id),
                { damgle ->
                    _damgle.emit(
                        ViewState.Success(
                            DamgleStoryBoxMapper.mapToModel(
                                damgle,
                                ReactionBoxState(null, false)
                            )
                        )
                    )
                },
                { error -> _damgle.emit(ViewState.Error(error.toString())) }
            )
        }
    }
}

