package com.example.data.managers

import com.example.data.api.models.ErrorParsableModel
import com.example.data.api.models.ResponseUnCoverModel
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.google.gson.Gson
import retrofit2.Response

class RetrofitErrorManager(
    private val serverErrorParser: ServerErrorParser,
    private val errorParsableModel: ErrorParsableModel
) {

    fun <T> getAuthState(response: Response<T>): AuthState =
        if (response.isSuccessful)
            AuthState.Success(true)
        else {
            val errorString =
                errorParsableModel.parseJson(response.errorBody()!!.string())
            val errorMap = serverErrorParser.parse(errorString)

            AuthState.Error(errorMap)
        }
}