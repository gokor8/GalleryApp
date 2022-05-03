package com.example.data.managers

import com.example.data.api.models.ErrorParsableModel
import com.example.data.core.auth.AuthServerState
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.states.AuthState
import retrofit2.Response

class AuthStateRetrofitErrorManager(
    serverErrorParser: ServerErrorParser,
    errorParsableModel: ErrorParsableModel
) : RetrofitErrorManager<AuthServerState>(
    serverErrorParser, errorParsableModel
) {
    override fun <T> getErrorState(response: Response<T>): AuthServerState {
        val errorString =
            errorParsableModel.parseJson(response.errorBody()!!.string())
        val errorMap = serverErrorParser.parse(errorString)

        return AuthServerState.ErrorData(errorMap)
    }
}