package com.mashup.damgledamgle.repository.token

import android.content.Context
import com.mashup.damgledamgle.domain.repository.TokenPreferenceRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *  TokenPreferenceRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class TokenPreferenceRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenPreferenceRepository {
    private val prefs =
        context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    override fun setToken(accessToken: String, refreshToken: String) {
        setString(KEY_ACCESS_TOKEN, accessToken)
        setString(KEY_REFRESH_TOKEN, refreshToken)
    }

    override fun getAccessToken(): String {
        return getString(KEY_ACCESS_TOKEN)
    }

    override fun getRefreshToken(): String {
        return getString(KEY_REFRESH_TOKEN)
    }

    private fun getString(key: String): String {
        return prefs.getString(key, "") ?: ""
    }

    private fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    companion object {
        private const val PREFERENCE_FILE_NAME = "DamgleDamgle"

        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}
