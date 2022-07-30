package com.mashup.damgledamgle.repository.spec

import com.squareup.moshi.Json

data class StoryFeedRequest(
    @Json(name = "top") val top: Double,
    @Json(name = "bottom") val bottom : Double,
    @Json(name = "left") val left : Double,
    @Json(name = "right") val right : Double
)
