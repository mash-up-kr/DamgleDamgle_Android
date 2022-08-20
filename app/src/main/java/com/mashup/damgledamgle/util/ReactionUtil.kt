package com.mashup.damgledamgle.util

import com.mashup.damgledamgle.domain.entity.Damgle

object ReactionUtil {

    fun getMainIcon(groupList : List<Damgle>) : String {
        var mainIcon = ""
        groupList.forEach { damgle ->
            var maxCount = 0
            damgle.reactionSummary.forEach { reaction ->
                if(maxCount < reaction.count) { //5를 dataclass reaction count로 변경
                    maxCount = reaction.count
                    mainIcon = reaction.type
                }
            }
        }
        return mainIcon
    }
}