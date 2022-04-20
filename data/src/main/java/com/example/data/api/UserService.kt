package com.example.data.api

import com.example.data.api.models.*
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    // Interceptors
    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun createUser(@Body userRequestInfo: RequestSignUpUserModel): Response<ResponseRegistration>

    @Headers("Content-Type: application/json")
    @GET("/oauth/v2/token")
    suspend fun loginUser(@QueryMap queryMap: Map<String, String>): Response<ResponseLogin>
}