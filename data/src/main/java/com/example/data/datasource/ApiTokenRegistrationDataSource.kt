package com.example.data.datasource

import com.example.data.api.TokenService
import javax.inject.Inject

class ApiTokenRegistrationDataSource @Inject constructor(
    private val tokenService: TokenService,
    private val defaultTokenId: String
) {

    suspend fun getUserToken(clientId: String) =
        tokenService.getTokenById(clientId)

    suspend fun getDefaultToken() =
        tokenService.getTokenById(defaultTokenId)
}