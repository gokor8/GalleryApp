package com.example.data.datasource

import com.example.data.api.models.*
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiAuthDataSource<I : SignInEntity, E : ErrorResponseModel>(
    protected val serverErrorParser: ServerErrorParser,
    protected val gson: Gson,
    private val responseErrorFillClass: Class<E>
) {
    abstract suspend fun sendAuthRequest(
        signInEntity: I,
    ): Response<*>

    suspend fun getSignState(
        signInEntity: I,
    ): AuthState {
        val userResponse = sendAuthRequest(signInEntity)

        return RetrofitResponseManager(
            serverErrorParser,
            ErrorGsonParsableModel(gson, responseErrorFillClass)
        ).getAuthState(userResponse)
    }
}