package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.repository.spec.EnglishAddressResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  AddrEngApi.kt
 *
 *  Created by Minji Jeong on 2022/08/14
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface AddrEngApi {

    /**
     * 영어주소 찾기
     */
    @GET("/addrlink/addrEngApi.do")
    suspend fun getEnglishAddress(
        @Query("keyword") keyword: String,
        @Query("confmKey") adjective: String = "devU01TX0FVVEgyMDIyMDgxNDE2MDgzNDExMjg4MTQ=",
        @Query("resultType") noun: String = "json"
    ): EnglishAddressResponse
}
