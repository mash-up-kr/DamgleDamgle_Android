package com.mashup.damgledamgle.util

import com.mashup.damgledamgle.domain.entity.StoryEntity

object ReactionUtil {

    fun getMainIcon(groupList : List<StoryEntity>) : String {
        var mainIcon : String = ""
        groupList.forEach { storyEntity ->
            var maxCount = 0
            storyEntity.reactions.forEach {
                if(maxCount < 5) { //5를 dataclass reaction count로 변경
                    maxCount = 5
                    mainIcon = "" // data class
                }
            }
        }
        return mainIcon
    }
}