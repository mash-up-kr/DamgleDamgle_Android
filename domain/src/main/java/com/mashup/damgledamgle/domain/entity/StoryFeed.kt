package com.mashup.damgledamgle.domain.entity

data class StoryFeed(
    val size : Int,
    val stories : List<StoryEntity>,
)

data class StoryEntity(
    val id : String,
    val userNo : Int,
    val nickname : String,
    val x : Double,
    val y : Double,
    val content : String,
    val reactions : List<ReactionType>,
    val createdAt : Long,
    val updatedAt : Long
)

data class ReactionType(
    val type : String
)
