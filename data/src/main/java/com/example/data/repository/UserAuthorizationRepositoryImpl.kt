package com.example.data.repository

import com.example.data.datasource.ApiAuthDataSource
import com.example.data.managers.ApiTokenManager
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserAuthorizationRepositoryImpl @Inject constructor(
    private val apiAuthDataSource: ApiAuthDataSource,
    private val apiTokenManager: ApiTokenManager
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext apiAuthDataSource.signUpUser(signUpEntity)
            }catch (e: Exception) {
                AuthState.Exception("Произошла ошибка с сетью.\r\nПовторите еще раз")
            }
        }
    }

    override suspend fun signInUser(signInEntity: SignInEntity): AuthState {

        return AuthState.Exception("Я ничего не написал тут, поэтому ошибка")//authCloudDataSource.signInUser(signInEntity)
    }
}
