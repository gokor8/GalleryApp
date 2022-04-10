package com.example.data.api

import com.example.data.api.entities.BodyNewTokenEntity
import com.example.data.api.entities.ResponseTokenEntity
import retrofit2.http.*

interface TokenService {

    @Headers("Content-Type: application/json")
    @POST("/api/clients")
    suspend fun getNewToken(@Body bodyNewTokenEntity: BodyNewTokenEntity): ResponseTokenEntity

    @Headers("Content-Type: application/json")
    @GET("/api/clients{clientId}")
    suspend fun getTokenById(@Path("clientId") clientId: String): ResponseTokenEntity

    @Headers("Content-Type: application/json")
    @PATCH("/api/clients{clientId}")
    suspend fun refreshTokenById(@Path("clientId") clientId: String): ResponseTokenEntity
}