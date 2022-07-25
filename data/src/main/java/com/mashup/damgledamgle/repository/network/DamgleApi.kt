package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.repository.spec.NickNameRequest
import retrofit2.http.GET

/**
 *  DamgleApi.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface DamgleApi {
    @GET("/v1/namepicker")
    suspend fun getNickName(): NickNameRequest
}
