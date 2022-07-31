package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryBoxModel
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.model.DamgleStoryReactionState
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.Reaction
import com.mashup.damgledamgle.presentation.feature.all_damgle_list.reaction.ReactionBoxState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class AllDamgleListViewModel @Inject constructor() : ViewModel() {

    var damgleSortStrategy by mutableStateOf(DamgleStorySort.LATEST)

    var damgleBoxState by mutableStateOf(
        mutableMapOf(
            "1" to DamgleStoryBoxModel(
                "1",
                1 to 1,
                "MAPOGU",
                "날카로운 11번째 원숭이",
                false,
                1659106750339L,
                "안녕하세요! 오늘은 이 카페에서 공감해조팀에서 회의를 하였어요~~ 다들 여기서 팀플해도 좋을듯용~~! 주인이 카공해도 뭐라고 안함~~참고하세요~~~!! 지금 넘 졸린데 오늘 일찍 자고 싶어서 커피를 마시지 않고 버틸 거에요~!!",
                mapOf(Reaction.AMAZING to DamgleStoryReactionState(0, 0)),
                null,
                ReactionBoxState(Reaction.AMAZING, false),
            ),
            "2" to DamgleStoryBoxModel(
                "2",
                1 to 1,
                "MAPOGU",
                "날카로운 11번째 원숭이",
                true,
                1659106750339L,
                "안녕하세요! 오늘은 이 카페에서 공감해조팀에서 회의를 하였어요~~ 다들 여기서 팀플해도 좋을듯용~~! 주인이 카공해도 뭐라고 안함~~참고하세요~~~!! 지금 넘 졸린데 오늘 일찍 자고 싶어서 커피를 마시지 않고 버틸 거에요~!!",
                mapOf(
                    Reaction.AMAZING to DamgleStoryReactionState(0, 2),
                    Reaction.SAD to DamgleStoryReactionState(1, 1),
                    Reaction.BEST to DamgleStoryReactionState(3, 3),
                    Reaction.LIKE to DamgleStoryReactionState(100, 4)
                ),
                null,
                ReactionBoxState(Reaction.AMAZING, false),
            ),
            "3" to DamgleStoryBoxModel(
                "3",
                1 to 1,
                "MAPOGU",
                "날카로운 11번째 원숭이",
                true,
                1659106750339L,
                "안녕하세요! 오늘은 이 카페에서 공감해조팀에서 회의를 하였어요~~ 다들 여기서 팀플해도 좋을듯용~~! 주인이 카공해도 뭐라고 안함~~참고하세요~~~!! 지금 넘 졸린데 오늘 일찍 자고 싶어서 커피를 마시지 않고 버틸 거에요~!!",
                mapOf(
                    Reaction.SAD to DamgleStoryReactionState(22, 5),
                    Reaction.AMAZING to DamgleStoryReactionState(0, 4),
                    Reaction.BEST to DamgleStoryReactionState(3, 3),
                    Reaction.LIKE to DamgleStoryReactionState(100, 2)
                ),
                null,
                ReactionBoxState(Reaction.AMAZING, false),
            ),
            "4" to DamgleStoryBoxModel(
                "4",
                1 to 1,
                "MAPOGU",
                "날카로운 11번째 원숭이",
                true,
                1659106750339L,
                "안녕하세요! 오늘은 이 카페에서 공감해조팀에서 회의를 하였어요~~ 다들 여기서 팀플해도 좋을듯용~~! 주인이 카공해도 뭐라고 안함~~참고하세요~~~!! 지금 넘 졸린데 오늘 일찍 자고 싶어서 커피를 마시지 않고 버틸 거에요~!!",
                mapOf(
                    Reaction.SAD to DamgleStoryReactionState(22, 5),
                ),
                null,
                ReactionBoxState(Reaction.AMAZING, false),
            )
        ),
    )

    fun reactMain(id: String) {
        damgleBoxState = damgleBoxState
            .toMutableMap()
            .apply {
                this[id]?.let {
                    this[id] = it.copy(reactionBoxState = it.reactionBoxState.copy(isExtended = !it.reactionBoxState.isExtended))
                }
            }
    }

    fun react(id: String, reaction: Reaction) {
        damgleBoxState = damgleBoxState
            .toMutableMap()
            .apply {
                this[id]?.let {
                    this[id] = it.copy(
                        reactionBoxState = it.reactionBoxState.copy(
                            selectedReaction = reaction,
                            isExtended = !it.reactionBoxState.isExtended
                        )
                    )
                }
            }
    }

    fun changeDamgleSortStrategy(damgleSortStrategy: DamgleStorySort) {
        this.damgleSortStrategy = damgleSortStrategy
    }
}
