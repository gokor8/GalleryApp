package com.example.data.storages

import com.example.domain.core.MapperFrom

class ClientKeysModel(accessToken: String, refreshToken: String) :
    MapperFrom<Map<String, String>, ClientKeysModel> {
    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    var accessToken: String = accessToken
        private set
    var refreshToken: String = refreshToken
        private set

    override fun mapTo(inputModel: Map<String, String>): ClientKeysModel {
        for (pairKeys in inputModel) {
            when (pairKeys.key) {
                ACCESS_TOKEN -> accessToken
                REFRESH_TOKEN -> refreshToken
            }
        }

        return this
    }
}