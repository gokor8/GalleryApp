package com.example.data.managers

import com.example.data.api.entities.ErrorUserResponse
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.google.gson.Gson
import retrofit2.Response

class RetrofitResponseManager(
    private val serverErrorParser: ServerErrorParser
) {

    fun<T> getAuthState(serverResponse: Response<T>): AuthState =
        if (serverResponse.isSuccessful)
            AuthState.Success(true)
        else {
            val errorString = Gson().fromJson(
                serverResponse.errorBody()!!.string(),
                ErrorUserResponse::class.java
            ).detail

            val errorMap = serverErrorParser.parse(errorString)

            AuthState.Error(errorMap)
        }
}