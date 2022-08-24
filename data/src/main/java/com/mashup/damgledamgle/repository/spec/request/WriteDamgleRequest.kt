package com.mashup.damgledamgle.repository.spec.request

import com.squareup.moshi.Json

data class WriteDamgleRequest(
    @Json(name = "x")
    val longitude: Double,
    @Json(name = "y")
    val latitude: Double,
    @Json(name = "content")
    val content: String,
    @Json(name = "address1")
    val address1: String,
    @Json(name = "address2")
    val address2: String,
)
