package com.example.data.repository

import com.example.data.datasource.CloudAuthDataSource
import com.example.data.datasource.CloudTokenDataSource
import com.example.data.managers.ApiTokenManager
import com.example.data.storages.CacheService
import com.example.domain.core.ValidationTypes
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserAuthorizationRepositoryImpl @Inject constructor(
    private val cloudAuthDataSource: CloudAuthDataSource,
    private val apiTokenManager: ApiTokenManager
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState {
        return withContext(Dispatchers.IO) {
            val responseData = cloudAuthDataSource.signUpUser(signUpEntity)

            return@withContext if (responseData != null)
                AuthState.Error(responseData)
            else
                AuthState.Success(true)
        }
    }

    override suspend fun signInUser(signInEntity: SignInEntity): AuthState {

        return AuthState.Error("Я ничего не написал тут, поэтому ошибка")//authCloudDataSource.signInUser(signInEntity)
    }
}
