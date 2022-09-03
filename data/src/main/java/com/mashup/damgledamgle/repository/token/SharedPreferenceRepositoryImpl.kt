package com.mashup.damgledamgle.repository.token

import android.content.Context
import com.mashup.damgledamgle.domain.repository.SharedPreferenceRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *  SharedPreferenceRepositoryImpl.kt
 *
 *  Created by Minji Jeong on 2022/07/30
 *  Copyright © 2022 MashUp All rights reserved.
 */

class SharedPreferenceRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SharedPreferenceRepository {
    private val prefs =
        context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    override fun getString(key: String): String {
        return prefs.getString(key, "") ?: ""
    }

    override fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    companion object {
        private const val PREFERENCE_FILE_NAME = "DamgleDamgle"
    }
}
