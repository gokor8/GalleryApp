package com.example.data.repository

import com.example.data.api.models.ErrorResponseModel
import com.example.data.datasource.auth.ApiSignInDataSource
import com.example.data.datasource.auth.ApiSignUpDataSource
import com.example.data.datasource.auth.BaseApiAuthDataSource
import com.example.data.storages.exceptions.CustomNoConnectionException
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

class UserAuthorizationRepositoryImpl @Inject constructor(
    private val apiSignUpDataSource: ApiSignUpDataSource,
    private val apiSignInDataSource: ApiSignInDataSource,
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState =
        apiAuthUser(signUpEntity, apiSignUpDataSource, Dispatchers.IO)

    override suspend fun signInUser(signInEntity: SignInEntity): AuthState =
        apiAuthUser(signInEntity, apiSignInDataSource, Dispatchers.IO)

    private suspend fun <I : SignInEntity, E : ErrorResponseModel, S> apiAuthUser(
        signInEntity: I,
        apiAuthDataSource: BaseApiAuthDataSource<I, E, S>,
        dispatcher: CoroutineDispatcher
    ): AuthState =
        try {
            withContext(dispatcher) {
                return@withContext apiAuthDataSource.getSignState(signInEntity)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> throw CustomNoConnectionException()
                else -> throw e
            }
        }
}
