package com.example.data.api.models.auth

import com.example.data.api.models.ErrorResponseModel
import com.google.gson.annotations.SerializedName

data class ResponseErrorSignUpModel(
    @SerializedName("detail")
    override val errorString: String,
    val title: String,
    val type: String,
) : ErrorResponseModel