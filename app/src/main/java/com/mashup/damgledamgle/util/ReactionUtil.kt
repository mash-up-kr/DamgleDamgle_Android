package com.mashup.damgledamgle.util

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.presentation.feature.home.model.MarkerModel

object ReactionUtil {

    fun getMainIcon(groupList : List<MarkerModel>) : String {
        var mainIcon : String? = null
        groupList.forEach { damgle ->
            mainIcon = damgle.damgle.reactionSummary.maxWithOrNull(
                Comparator.comparing {
                    it.count
                }
            )?.type
        }
        return if(mainIcon == null) "best" else mainIcon as String
    }
}