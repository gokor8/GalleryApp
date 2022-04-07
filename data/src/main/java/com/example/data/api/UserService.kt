package com.example.data.api

import com.example.data.api.entities.RegUserRequestEntity
import com.example.data.api.entities.RegUserResponseEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun createUser(@Body userRequestInfo: RegUserRequestEntity): Response<RegUserResponseEntity>
}