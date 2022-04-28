package com.example.data.managers

import com.example.data.api.models.ErrorParsableModel
import com.example.data.parsers.ServerErrorParser
import com.example.domain.core.ErrorType
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.states.PhotosState
import retrofit2.Response

class PhotosStateRetrofitErrorManager(
    serverErrorParser: ServerErrorParser,
    errorParsableModel: ErrorParsableModel
) : RetrofitErrorManager<PhotosState>(
    serverErrorParser, errorParsableModel
) {
    override fun <T> getErrorState(response: Response<T>) = PhotosState.Error()
}