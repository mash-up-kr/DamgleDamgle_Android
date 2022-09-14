package com.mashup.damgledamgle.domain.usecase.user

import com.mashup.damgledamgle.domain.entity.base.Result
import com.mashup.damgledamgle.domain.repository.DataStoreRepository
import com.mashup.damgledamgle.domain.repository.UserRepository
import javax.inject.Inject

class GetMyIdUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Int> {
        val id = dataStoreRepository.getId()
        return if (id != -1) {
            Result.Success(id)
        } else {
            val result = userRepository.getUserProfile()

            return if (result is Result.Success) {
                dataStoreRepository.setId(result.data.userNo)
                Result.Success(result.data.userNo)
            } else {
                result as Result.Error
            }
        }
    }
}
