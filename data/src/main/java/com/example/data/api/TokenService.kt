package com.example.data.api

import com.example.data.api.models.RequestNewTokenModel
import com.example.data.api.models.ResponseTokenModel
import retrofit2.http.*

interface TokenService {

    /*@Headers("Content-Type: application/json")
    @POST("/api/clients")
    suspend fun getNewToken(@Body requestNewTokenModel: RequestNewTokenModel): ResponseTokenModel*/

    @Headers("Content-Type: application/json")
    @GET("/api/clients/{clientId}")
    suspend fun getTokenById(@Path("clientId") clientId: String): ResponseTokenModel

    /*@Headers("Content-Type: application/json")
    @PATCH("/api/clients{clientId}")
    suspend fun refreshTokenById(@Path("clientId") clientId: String): ResponseTokenModel*/
}