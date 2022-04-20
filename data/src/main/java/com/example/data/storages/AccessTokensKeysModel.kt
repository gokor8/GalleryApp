package com.example.data.storages

import com.example.domain.core.MapperFrom

class AccessTokensKeysModel(accessToken: String, refreshToken: String) :
    MapperFrom<Map<String, String?>, AccessTokensKeysModel> {
    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    constructor() : this("", "")

    var accessToken: String = accessToken
        private set
    var refreshToken: String = refreshToken
        private set

    override fun mapTo(inputModel: Map<String, String?>): AccessTokensKeysModel {
        for (pairKeys in inputModel) {
            val pairValue = pairKeys.value ?: ""
            when (pairKeys.key) {
                ACCESS_TOKEN -> accessToken = pairValue
                REFRESH_TOKEN -> refreshToken = pairValue
            }
        }

        return this
    }

    fun hasEmpty() =
        listOf(accessToken, refreshToken).any { it.isEmpty() }
}