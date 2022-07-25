package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.repository.spec.NickNameRequest
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  DamgleApi.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface DamgleApi {
    @GET("/v1/namepicker")
    suspend fun getNickName(
        @Query("adjective") adjective: String? = null,
        @Query("noun") noun: String? = null
    ): NickNameRequest
}
