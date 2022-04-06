package com.example.data.api

import com.example.domain.entities.api.RegistrateUserEntity
import com.example.domain.entities.api.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun createUser(@Body userInfo: RegistrateUserEntity): Response<UserResponse>
}