package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.repository.spec.NaverGeocodeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApi {

    @GET("/map-reversegeocode/v2/gc")
    suspend fun getReverseGeocoding(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId : String,
        @Header("X-NCP-APIGW-API-KEY") clientKey : String,
        @Query("coords") coors: String?,
        @Query("orders") orders: String = "roadaddr",
        @Query("output") output : String = "json"
    ) : NaverGeocodeResponse

}