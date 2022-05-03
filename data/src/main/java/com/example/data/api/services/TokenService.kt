package com.example.data.api.services

import com.example.data.api.models.ResponseTokenModel
import retrofit2.http.*

interface TokenService {

    @Headers("Content-Type: application/json")
    @GET("/api/clients/{clientId}")
    suspend fun getTokenById(@Path("clientId") clientId: String): ResponseTokenModel
}