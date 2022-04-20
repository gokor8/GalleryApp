package com.example.data.api.models

import com.google.gson.annotations.SerializedName

class ResponseErrorSignInModel(val error: String, errorDescription: String): ErrorResponseModel {
    @SerializedName("error_description")
    override val errorString: String  = errorDescription
}