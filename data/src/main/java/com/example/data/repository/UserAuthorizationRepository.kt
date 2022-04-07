package com.example.data.repository

import com.example.data.api.ApiRegistrator
import com.example.data.storages.CacheService
import com.example.data.storages.KeysEntity
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import javax.inject.Inject

class UserAuthorizationRepository @Inject constructor(
    private val apiRegistrator: ApiRegistrator,
    private val cacheService: CacheService
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): String? {
        val responseData = apiRegistrator.signUpUser(signUpEntity)

        return if (responseData != null) {
            cacheService.saveKeys(
                KeysEntity(
                    responseData.id.toString(),
                    ""
                )
            )
            null
        } else {
            "Error"
        }
    }

    override suspend fun signInUser(signInEntity: SignInEntity): String? {
        return null//apiRegistrator.signInUser(signInEntity)
    }

    suspend fun authorizationClient() {

    }
}