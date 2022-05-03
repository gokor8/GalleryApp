package com.example.data.managers

import com.example.data.api.models.ErrorParsableModel
import com.example.data.parsers.ServerErrorParser
import com.example.domain.core.MapperTo
import com.example.domain.entities.states.AuthState
import retrofit2.Response

abstract class RetrofitErrorManager<R : MapperTo<AuthState>>(
    protected val serverErrorParser: ServerErrorParser,
    protected val errorParsableModel: ErrorParsableModel,
) {
    abstract fun <T> getErrorState(response: Response<T>): R
}