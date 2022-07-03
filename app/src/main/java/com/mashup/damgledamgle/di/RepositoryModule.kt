package com.mashup.damgledamgle.di

import com.mashup.damgledamgle.domain.repository.DataStoreRepository
import com.mashup.damgledamgle.repository.datastore.DataStoreRepositoryImpl
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
    abstract fun provideDataStoreRepository(impl: DataStoreRepositoryImpl): DataStoreRepository
}
