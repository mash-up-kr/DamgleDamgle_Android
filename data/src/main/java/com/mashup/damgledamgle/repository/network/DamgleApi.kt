package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.repository.spec.*
import com.mashup.damgledamgle.repository.spec.NickNameRequest
import com.mashup.damgledamgle.repository.spec.NickNameResponse
import com.mashup.damgledamgle.repository.spec.PickNickNameRequest
import com.mashup.damgledamgle.repository.spec.UserResponse
import retrofit2.http.*

/**
 *  DamgleApi.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface DamgleApi {

    /**
     * 닉네임 생성
     */
    @GET("/v1/namepicker")
    suspend fun getNickName(
        @Query("adjective") adjective: String? = null,
        @Query("noun") noun: String? = null
    ): NickNameResponse

    /**
     * 닉네임 순번 확정
     */
    @POST("/v1/namepicker")
    suspend fun pickNickName(@Body request: PickNickNameRequest): NickNameResponse

    /**
     * Auth - SignUp
     */
    @POST("/v1/auth/signup")
    suspend fun signUp(@Body request: NickNameRequest): UserResponse

    @GET("/v1/story/feed")
    suspend fun getStoryFeed(
        @Body storyFeedRequest: StoryFeedRequest) : StoryFeedResponse
}
