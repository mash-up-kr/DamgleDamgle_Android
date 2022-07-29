package com.mashup.damgledamgle.repository.network

import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.usecase.token.GetTokenUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *  ServiceBuilder.kt
 *
 *  Created by Minji Jeong on 2022/07/25
 *  Copyright © 2022 MashUp All rights reserved.
 */

class ServiceBuilder @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val levelType = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(levelType)
    }

    private val authInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = getTokenUseCase.invoke()

            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

            return chain.proceed(request)
        }
    }

    private val timeout: Long = 60
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            addNetworkInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)
        }.build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api-dev.damgle.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    inline fun <reified T> buildService(): T {
        return retrofit.create(T::class.java)
    }
}
