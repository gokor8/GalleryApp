package com.example.data.datasource

import com.example.data.api.TokenService
import com.example.data.api.models.RequestNewTokenModel
import javax.inject.Inject
import javax.inject.Named

class ApiTokenRegistrationDataSource @Inject constructor(
    private val tokenService: TokenService,
    private val defaultTokenId: String
) {

    suspend fun getUserToken(clientId: String) =
        tokenService.getTokenById(clientId)

    suspend fun getDefaultToken() =
        tokenService.getTokenById(defaultTokenId)
}