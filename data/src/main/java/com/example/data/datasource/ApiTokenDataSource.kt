package com.example.data.datasource

import com.example.data.api.TokenService
import com.example.data.api.models.RequestNewTokenModel
import javax.inject.Inject

class ApiTokenDataSource @Inject constructor(private val tokenService: TokenService) {

    suspend fun getAvailableToken(clientId: String) =
        tokenService.getTokenById(clientId)

    suspend fun getNewToken() =
        tokenService.getNewToken(RequestNewTokenModel("WebAntAndroidApp", listOf("Aboba")))
}