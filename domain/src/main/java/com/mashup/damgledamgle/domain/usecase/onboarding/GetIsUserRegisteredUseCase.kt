package com.mashup.damgledamgle.domain.usecase.onboarding

/**
 *  GetIsUserRegisteredUseCase.kt
 *
 *  Created by Minji Jeong on 2022/07/04
 *  Copyright © 2022 MashUp All rights reserved.
 */

interface GetIsUserRegisteredUseCase {
    suspend operator fun invoke(): Boolean
}
