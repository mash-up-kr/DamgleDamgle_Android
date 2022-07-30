package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

data class StoryFeedResponse(
    @Json(name = "size") val size : Int,
    @Json(name = "stories") val stories : List<Stories>,
)

data class Stories(
    @Json(name = "id") val id : String,
    @Json(name = "reactions") val reactions : List<Reaction>,
    @Json(name = "x") val x : Float,
    @Json(name = "y") val y : Float
)

data class Reaction(
    @Json(name = "type") val type : String
)
