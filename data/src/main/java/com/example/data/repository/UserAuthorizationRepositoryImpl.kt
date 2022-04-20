package com.example.data.repository

import com.example.data.api.models.ErrorResponseModel
import com.example.data.datasource.ApiSignInDataSource
import com.example.data.datasource.ApiSignUpDataSource
import com.example.data.datasource.BaseApiAuthDataSource
import com.example.data.managers.ApiTokenManagerBabijon
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.example.domain.repository.AuthorizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserAuthorizationRepositoryImpl @Inject constructor(
    private val apiSignUpDataSource: ApiSignUpDataSource,
    private val apiSignInDataSource: ApiSignInDataSource,
) : AuthorizationRepository {

    override suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState =
        apiAuthUser(signUpEntity, apiSignUpDataSource, Dispatchers.IO)

    override suspend fun signInUser(signInEntity: SignInEntity): AuthState =
        apiAuthUser(signInEntity, apiSignInDataSource, Dispatchers.IO)

    private suspend fun <I : SignInEntity, E : ErrorResponseModel> apiAuthUser(
        signInEntity: I,
        apiAuthDataSource: BaseApiAuthDataSource<I, E>,
        dispatcher: CoroutineDispatcher
    ): AuthState {
        return withContext(dispatcher) {
            return@withContext apiAuthDataSource.getSignState(signInEntity)
        }
    }
}
