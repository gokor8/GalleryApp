package com.example.data.managers

import com.example.data.api.models.ErrorParsableModel
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.google.gson.Gson
import retrofit2.Response

class RetrofitResponseManager(
    private val serverErrorParser: ServerErrorParser,
    private val errorParsableModel: ErrorParsableModel
) {

    fun<T> getAuthState(serverResponse: Response<T>): AuthState =
        if (serverResponse.isSuccessful)
            AuthState.Success(true)
        else {
            val errorString = errorParsableModel.parseJson(serverResponse.errorBody()!!.string())
            val errorMap = serverErrorParser.parse(errorString)

            AuthState.Error(errorMap)
        }
}