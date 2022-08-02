package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.repository.spec.*
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

    /**
     * Auth - Me : 내 정보
     */
    @GET("/v1/auth/me")
    suspend fun getUserProfile(): UserProfileResponse

    /**
     * Auth - Delete Me : 계정 삭제
     */
    @DELETE("v1/auth/deleteme")
    suspend fun deleteMe(): UserDeleteResponse

    /**
     * Auth - Notify : 푸시 알림 설정
     */
    @PATCH("v1/auth/notify")
    suspend fun switchNotification(): NotifyResponse

    /**
     * Story - me : 내 담글 조회
     */
    @GET("v1/story/me")
    suspend fun getMyDamgleList(): DamgleListResponse
}
