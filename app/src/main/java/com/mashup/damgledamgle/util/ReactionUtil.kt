package com.mashup.damgledamgle.util

import com.mashup.damgledamgle.commonModel.DamgleModel
import com.mashup.damgledamgle.domain.entity.Damgle

object ReactionUtil {

    fun getMainIconFromGroupList(groupList : List<Damgle>): String {
        val iconMap = mutableMapOf<String,Int>()
        groupList.forEach { damgle ->
            damgle.reactionSummary.forEach {
                if(iconMap[it.type] != null) {
                    iconMap[it.type] = it.count + iconMap[it.type]!!
                } else {
                    iconMap[it.type] = it.count
                }
            }
        }
        val maxIcon = iconMap.maxByOrNull {it.value}
        return if(maxIcon?.key == null || maxIcon.value == 0) "nothing"
        else maxIcon.key
    }

    fun getMainIconFromReactionSummaryList(reactionList: List<DamgleModel.ReactionSummary>): String {
        val maxCount = reactionList.maxOf { it.count }
        return if (maxCount < 1) {
            "nothing"
        } else {
            reactionList.find { it.count == maxCount }?.type ?: "nothing"
        }
    }
}