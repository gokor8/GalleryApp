package com.example.data.api.models

import com.example.data.storages.models.AccessTokensKeysModel
import com.example.domain.core.MapperTo
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
) : MapperTo<AccessTokensKeysModel> {

    override fun mapTo(): AccessTokensKeysModel = AccessTokensKeysModel(accessToken, refreshToken)
}