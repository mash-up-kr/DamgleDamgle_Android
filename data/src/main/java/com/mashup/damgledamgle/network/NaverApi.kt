package com.mashup.damgledamgle.network

import com.mashup.damgledamgle.repository.spec.NaverGeocodeResponse
import retrofit2.http.*

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
