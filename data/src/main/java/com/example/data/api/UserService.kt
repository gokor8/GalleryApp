package com.example.data.api

import com.example.data.api.models.RequestSignInUserModel
import com.example.data.api.models.RequestSignUpUserModel
import com.example.data.api.models.ResponseTokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    // Interceptors
    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun createUser(@Body userRequestInfo: RequestSignUpUserModel): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun loginUser(@Body userRequestInfo: RequestSignInUserModel): Response<ResponseTokenModel>
}