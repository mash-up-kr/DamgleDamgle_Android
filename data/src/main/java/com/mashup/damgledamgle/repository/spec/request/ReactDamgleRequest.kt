package com.mashup.damgledamgle.repository.spec.request

import com.squareup.moshi.Json

data class ReactDamgleRequest(
    @Json(name = "type")
    val reaction: String
)
