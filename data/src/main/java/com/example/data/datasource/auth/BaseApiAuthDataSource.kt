package com.example.data.datasource.auth

import com.example.data.api.models.*
import com.example.data.api.models.auth.ErrorResponseModel
import com.example.data.core.auth.AuthServerState
import com.example.data.managers.AuthStateRetrofitErrorManager
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.auth.SignInEntity
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiAuthDataSource<I : SignInEntity, E : ErrorResponseModel, S>(
    private val serverErrorParser: ServerErrorParser,
    protected val gson: Gson,
    private val responseErrorFillClass: Class<E>
) {
    abstract suspend fun sendAuthRequest(
        authEntity: I,
    ): Response<S>

    protected open suspend fun onSuccess(authEntity: I, responseModel: S): AuthServerState =
        AuthServerState.Success()

    suspend fun getAuthorizationState(
        authEntity: I,
    ): AuthServerState {
        val userResponse = sendAuthRequest(authEntity)

        return if (userResponse.isSuccessful) {
            val responseModel =
                userResponse.body() ?: throw Exception("Модель не подошла под ответ сервера")
            onSuccess(authEntity, responseModel)
        } else {
            AuthStateRetrofitErrorManager(
                serverErrorParser,
                ErrorGsonParsableModel(gson, responseErrorFillClass)
            ).getErrorState(userResponse)
        }
    }
}