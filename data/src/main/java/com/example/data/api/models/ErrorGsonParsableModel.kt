package com.example.data.api.models

import com.example.data.api.models.auth.ErrorResponseModel
import com.google.gson.Gson

class ErrorGsonParsableModel<G : ErrorResponseModel>(
    private val gson: Gson,
    private val fillClass: Class<G>
) : ErrorParsableModel {

    override fun parseJson(serverErrorResponse: String): String =
        gson.fromJson(
            serverErrorResponse,
            fillClass
        ).errorString
}