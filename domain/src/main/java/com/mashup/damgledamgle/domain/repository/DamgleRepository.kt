package com.mashup.damgledamgle.domain.repository

import com.mashup.damgledamgle.domain.entity.Damgle
import com.mashup.damgledamgle.domain.entity.base.NetworkResponse

interface DamgleRepository {
    suspend fun getMyDamgleList(): NetworkResponse<List<Damgle>>
}