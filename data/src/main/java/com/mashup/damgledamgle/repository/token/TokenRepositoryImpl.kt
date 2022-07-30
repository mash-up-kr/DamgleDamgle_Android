package com.mashup.damgledamgle.repository.token

import android.content.Context
import com.mashup.damgledamgle.domain.repository.TokenRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *  TokenRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class TokenRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenRepository {
    private val prefs =
        context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    override fun getToken(): String {
        return prefs.getString(KEY_ACCESS_TOKEN, "") ?: ""
    }

    override fun getRefreshToken(): String {
        return prefs.getString(KEY_REFRESH_TOKEN, "") ?: ""
    }

    override fun setToken(accessToken: String, refreshToken: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply()
        prefs.edit().putString(KEY_REFRESH_TOKEN, accessToken).apply()
    }

    companion object {
        private const val PREFERENCE_FILE_NAME = "DamgleDamgle"

        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}
