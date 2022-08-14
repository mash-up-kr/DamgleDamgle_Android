package com.mashup.damgledamgle.di

import com.mashup.damgledamgle.domain.repository.*
import com.mashup.damgledamgle.repository.datastore.DataStoreRepositoryImpl
import com.mashup.damgledamgle.repository.remote.DamgleRepositoryImpl
import com.mashup.damgledamgle.repository.remote.EnglishAddressRepositoryImpl
import com.mashup.damgledamgle.repository.remote.OnboardingRepositoryImpl
import com.mashup.damgledamgle.repository.remote.UserRepositoryImpl
import com.mashup.damgledamgle.repository.token.TokenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  RepositoryModule.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindDataStoreRepository(impl: DataStoreRepositoryImpl): DataStoreRepository

    @Singleton
    @Binds
    abstract fun bindOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

    @Singleton
    @Binds
    abstract fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository

    @Singleton
    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindDamgleRepository(impl: DamgleRepositoryImpl): DamgleRepository

    @Singleton
    @Binds
    abstract fun bindEnglishAddressRepository(impl: EnglishAddressRepositoryImpl): EnglishAddressRepository
}
