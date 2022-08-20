package com.mashup.damgledamgle.network

import android.util.Log
import com.mashup.damgledamgle.data.BuildConfig
import com.mashup.damgledamgle.domain.usecase.token.GetRefreshTokenUseCase
import com.mashup.damgledamgle.domain.usecase.token.GetTokenUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val BASE_URL = "https://api-dev.damgle.com/"
    private const val NAVER_URL = "https://naveropenapi.apigw.ntruss.com/"
    private const val TIME_OUT = 60L

    @Provides
    @Singleton
    fun provideOkHttpClient(
        getTokenUseCase: GetTokenUseCase,
        getRefreshTokenUseCase: GetRefreshTokenUseCase
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })
            addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val token = getTokenUseCase()
                    val refreshToken = getRefreshTokenUseCase()

                    Log.d("TAG", "TOKEN: $token, REFRESH_TOKEN: $refreshToken")

                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()

                    return chain.proceed(request)
                }
            })
        }.build()
    }

    @Provides
    @Singleton
    fun provideDamgleApi(
        okHttpClient: OkHttpClient
    ): DamgleApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(DamgleApi::class.java)

    @Provides
    @Singleton
    fun provideNaverApi(
        okHttpClient: OkHttpClient
    ): NaverApi =
        Retrofit.Builder()
            .baseUrl(NAVER_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(NaverApi::class.java)
}

