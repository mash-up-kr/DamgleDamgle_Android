package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

data class StoryFeedResponse(
    @Json(name = "size") val size : Int,
    @Json(name = "stories") val stories : List<Stories>,
)

data class Stories(
    @Json(name = "id") val id : String,
    @Json(name = "userNo") val userNo : Int,
    @Json(name = "nickname") val nickname : String,
    @Json(name = "x") val x : Double,
    @Json(name = "y") val y : Double,
    @Json(name = "content") val content : String,
    @Json(name = "reactions") val reactions : List<Reactions>,
    @Json(name = "createdAt") val createdAt : Long,
    @Json(name = "updatedAt") val updatedAt : Long
)

data class Reactions(
    @Json(name = "type") val type : String
)
