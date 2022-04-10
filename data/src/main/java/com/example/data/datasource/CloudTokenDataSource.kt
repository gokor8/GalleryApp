package com.example.data.datasource

import com.example.data.api.TokenService
import com.example.data.api.entities.BodyNewTokenEntity
import javax.inject.Inject

class CloudTokenDataSource @Inject constructor(private val tokenService: TokenService) {

    suspend fun getAvailableToken(clientId: String) =
        tokenService.getTokenById(clientId)

    suspend fun getNewToken() =
        tokenService.getNewToken(BodyNewTokenEntity("WebAntAndroidApp", listOf("Aboba")))
}