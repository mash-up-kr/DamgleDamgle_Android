package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.damgledamgle.domain.entity.base.launchWithResult
import com.mashup.damgledamgle.domain.usecase.damgle.*
import com.mashup.damgledamgle.domain.usecase.user.GetMyIdUseCase
import com.mashup.damgledamgle.enumerate.*
import com.mashup.damgledamgle.presentation.common.ViewState
import com.mashup.damgledamgle.presentation.common.successOrNull
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxMapper
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class AllDamgleListViewModel @Inject constructor(
    private val getDamgleStoryUseCase: GetDamgleStoriesUseCase,
    private val getMyIdUseCase: GetMyIdUseCase,
    private val createDamgleReactionUseCase: CreateDamgleReactionUseCase,
    private val deleteDamgleReactionUseCase: DeleteDamgleReactionUseCase,
    private val reportDamgleUseCase: ReportDamgleUseCase
) : ViewModel() {

    private val myId = MutableStateFlow(-1)

    private val _damgleSortStrategy = MutableStateFlow(DamgleStorySort.LATEST)
    val damgleSortStrategy: StateFlow<DamgleStorySort> = _damgleSortStrategy

    private val _damgleFeedState = MutableStateFlow<ViewState<MutableMap<String, DamgleStoryBoxModel>>>(ViewState.Loading)
    val damgleFeedState: StateFlow<ViewState<List<DamgleStoryBoxModel>>> =
        combine(_damgleFeedState, _damgleSortStrategy, myId) { stories, strategy, id ->
            when (stories) {
                is ViewState.Loading -> ViewState.Loading
                is ViewState.Error -> ViewState.Error(stories.error)
                is ViewState.Success -> ViewState.Success(
                    stories.data.values
                        .toList()
                        .sortedBy { model ->
                            when (strategy) {
                                DamgleStorySort.LATEST -> -model.dateTime
                                DamgleStorySort.POPULAR -> -model.reactions.values.sumOf { it.count }.toLong()
                            }
                        }
                        .filter { !it.reports.contains(id) }
                )
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ViewState.Loading)

    fun init(
        top: Double,
        bottom: Double,
        left: Double,
        right: Double
    ) {
        viewModelScope.launch {
            // TODO 합쳐야함
            launchWithResult(
                getMyIdUseCase(),
                { id -> myId.emit(id) },
                {}
            )
            launchWithResult(
                getDamgleStoryUseCase(top, bottom, left, right),
                { data ->
                    _damgleFeedState.emit(
                        ViewState.Success(
                            data
                                .map { damgle ->
                                    (DamgleStoryBoxMapper.mapToModel(
                                        damgle,
                                        ReactionBoxState(damgle.reactionOfMine?.type?.toReaction(), false)
                                    ))
                                }
                                .associateBy { it.id }
                                .toMutableMap()
                        )
                    )
                },
                { error -> _damgleFeedState.emit(ViewState.Error(error.toString())) }
            )
        }
    }

    fun extendReactionBox(id: String) {
        viewModelScope.launch {
            ViewState.Success(_damgleFeedState.value.successOrNull()
                ?.toMutableMap()
                ?.apply {
                    this[id]?.let {
                        this[id] = it.copy(reactionBoxState = it.reactionBoxState.copy(isExtended = true))
                    }
                }
                ?.let {
                    _damgleFeedState.emit(ViewState.Success(it))
                }
            )
        }
    }

    fun reactDamgle(id: String, reaction: Reaction) {
        viewModelScope.launch {
            launchWithResult(
                createDamgleReactionUseCase(storyId = id, reaction = reaction.toEnglish()),
                { damgle ->
                    ViewState.Success(_damgleFeedState.value.successOrNull()
                        ?.toMutableMap()
                        ?.apply {
                            this[id] = DamgleStoryBoxMapper.mapToModel(
                                damgle,
                                ReactionBoxState(reaction, false)
                            )
                        }
                        ?.let {
                            _damgleFeedState.emit(ViewState.Success(it))
                        }
                    )
                },
                { error -> _damgleFeedState.emit(ViewState.Error(error.toString())) }
            )
        }
    }

    fun deleteReaction(id: String) {
        viewModelScope.launch {
            launchWithResult(
                deleteDamgleReactionUseCase(storyId = id),
                { damgle ->
                    ViewState.Success(_damgleFeedState.value.successOrNull()
                        ?.toMutableMap()
                        ?.apply {
                            this[id] = DamgleStoryBoxMapper.mapToModel(
                                damgle,
                                ReactionBoxState(null, false)
                            )
                        }
                        ?.let {
                            _damgleFeedState.emit(ViewState.Success(it))
                        }
                    )
                },
                { error -> _damgleFeedState.emit(ViewState.Error(error.toString())) }
            )
        }
    }

    fun changeDamgleSortStrategy(damgleSortStrategy: DamgleStorySort) {
        viewModelScope.launch {
            _damgleSortStrategy.emit(damgleSortStrategy)
        }
    }

    fun reportDamgle(storyId: String) {
        viewModelScope.launch {
            launchWithResult(
                reportDamgleUseCase(storyId),
                { damgle ->
                    ViewState.Success(_damgleFeedState.value.successOrNull()
                        ?.toMutableMap()
                        ?.apply {
                            this[storyId] = DamgleStoryBoxMapper.mapToModel(
                                damgle,
                                ReactionBoxState(null, false)
                            )
                        }
                        ?.let {
                            _damgleFeedState.emit(ViewState.Success(it))
                        }
                    )
                },
                { error -> _damgleFeedState.emit(ViewState.Error(error.toString())) }
            )
        }
    }
}

enum class DamgleStorySort(val key: String) {
    LATEST("최신순"), POPULAR("인기순")
}
