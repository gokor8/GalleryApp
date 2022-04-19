package com.example.data.datasource

import com.example.data.api.models.*
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.ClientKeysModel
import com.example.domain.core.MapperFrom
import com.example.domain.core.MapperTo
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseApiAuthDataSource<I : SignInEntity, E : ErrorResponseModel>(
//<I : SignInEntity, E : ErrorResponseModel, M : MapperFrom<Map<String, String>, M>>(
    protected val serverErrorParser: ServerErrorParser,
    protected val gson: Gson,
    private val responseErrorFillClass: Class<E>
) {
    abstract suspend fun sendAuthRequest(
        signInEntity: I,
        //keysModel: M
    ): Response<*>

    suspend fun getSignState(
        signInEntity: I,
        //keysModel: M
    ): AuthState {
        val userResponse = sendAuthRequest(signInEntity)//, keysModel)

        return RetrofitResponseManager(
            serverErrorParser,
            ErrorGsonParsableModel(gson, responseErrorFillClass)
        ).getAuthState(userResponse)
    }
}