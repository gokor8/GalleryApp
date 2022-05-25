package com.example.data.repository

import com.example.data.api.models.auth.ErrorResponseModel
import com.example.data.datasource.auth.ApiSignInDataSource
import com.example.data.datasource.auth.ApiSignUpDataSource
import com.example.data.datasource.auth.BaseApiAuthDataSource
import com.example.data.datasource.auth.CheckApiKeysDataSource
import com.example.data.storages.exceptions.CustomNoConnectionException
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.auth.SignInEntity
import com.example.domain.entities.auth.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.SocketTimeoutException

class UserAuthorizationRepositoryImpl constructor(
    private val apiSignUpDataSource: ApiSignUpDataSource,
    private val apiSignInDataSource: ApiSignInDataSource,
    private val checkApiKeysDataSource: CheckApiKeysDataSource
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState =
        apiAuthUser(signUpEntity, apiSignUpDataSource, Dispatchers.IO)

    override suspend fun signInUser(signInEntity: SignInEntity): AuthState =
        apiAuthUser(signInEntity, apiSignInDataSource, Dispatchers.IO)

    //override suspend fun isUserAuthorizate(): AuthState = checkApiKeysDataSource.checkAndRefreshApiKeys()

    private suspend fun <I : SignInEntity, E : ErrorResponseModel, S> apiAuthUser(
        signInEntity: I,
        apiAuthDataSource: BaseApiAuthDataSource<I, E, S>,
        dispatcher: CoroutineDispatcher
    ): AuthState =
        try {
            withContext(dispatcher) {
                return@withContext apiAuthDataSource.getAuthorizationState(signInEntity).mapTo()
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> throw CustomNoConnectionException()
                else -> throw e
            }
        }
}
