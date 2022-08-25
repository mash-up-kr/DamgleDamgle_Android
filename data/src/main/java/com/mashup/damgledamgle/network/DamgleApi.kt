package com.mashup.damgledamgle.network

import com.mashup.damgledamgle.repository.spec.request.ReactDamgleRequest
import com.mashup.damgledamgle.repository.spec.request.WriteDamgleRequest
import com.mashup.damgledamgle.repository.spec.response.*
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
     * Map - Story Feed 가져오기
     * */
    @GET("/v1/story/feed")
    suspend fun getStoryFeed(
        @Query("top") top: Double,
        @Query("bottom") bottom: Double,
        @Query("left") left: Double,
        @Query("right") right: Double
    ): DamgleListResponse

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
     * Story : 담글 작성
     */
    @POST("v1/story")
    suspend fun writeDamgle(@Body body: WriteDamgleRequest): DamgleResponse

    /**
     * Story : 담글 리액션
     */
    @POST("v1/story/react/{storyId}")
    suspend fun createDamgleReaction(@Body body: ReactDamgleRequest, @Path("storyId") storyId: String): DamgleResponse

    /**
     * Story : 담글 리액션 제거
     */
    @DELETE("v1/story/react/{storyId}")
    suspend fun deleteDamgleReaction(@Path("storyId") storyId: String): DamgleResponse

    /**
     * Story - me : 내 담글 조회
     */
    @GET("v1/story/me")
    suspend fun getMyDamgleList(): DamgleListResponse

    /**
     * Story : 담글 조회
     */
    @GET("v1/story/{id}")
    suspend fun getDamgle(@Path("id") id: String): DamgleResponse

}
