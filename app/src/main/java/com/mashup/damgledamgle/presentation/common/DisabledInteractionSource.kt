package com.mashup.damgledamgle.presentation.common

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 *  DisabledInteractionSource.kt
 *
 *  Created by Minji Jeong on 2022/07/09
 *  Copyright © 2022 MashUp All rights reserved.
 *
 *  터치 효과(Ripple) 없애기 위해 사용
 *  ex) interactionSource = remember { DisabledInteractionSource() }
 *
 */

//class DisabledInteractionSource : MutableInteractionSource {
//
//    override val interactions: Flow<Interaction> = emptyFlow()
//
//    override suspend fun emit(interaction: Interaction) {}
//
//    override fun tryEmit(interaction: Interaction) = true
//
//}
