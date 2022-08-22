package com.mashup.damgledamgle.util

import com.mashup.damgledamgle.domain.entity.Damgle

object ReactionUtil {

    fun getMainIcon(groupList : List<Damgle>) : String {
        var mainIcon : String? = null
        groupList.forEach { damgle ->
            mainIcon = damgle.reactionSummary.maxWithOrNull(
                Comparator.comparing {
                    it.count
                }
            )?.type
        }
        return if(mainIcon == null) "nothing" else mainIcon as String
    }
}