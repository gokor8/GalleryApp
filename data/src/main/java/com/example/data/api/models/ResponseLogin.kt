package com.example.data.api.models

import com.example.data.storages.AccessTokensKeysModel
import com.example.domain.core.MapperTo

data class ResponseLogin(val accessToken: String, val refreshToken: String): MapperTo<AccessTokensKeysModel> {

    override fun mapTo(): AccessTokensKeysModel = AccessTokensKeysModel(accessToken, refreshToken)
}