package com.mashup.damgledamgle.di

import com.mashup.damgledamgle.domain.usecase.onboarding.GetIsUserRegisteredUseCase
import com.mashup.damgledamgle.domain.usecase.onboarding.GetIsUserRegisteredUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  UseCaseModule.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun provideGetIsUserRegisteredUseCase(impl: GetIsUserRegisteredUseCaseImpl): GetIsUserRegisteredUseCase
}
