package com.example.data.api

import android.util.Log
import com.example.data.api.entities.ErrorUserResponse
import com.example.data.api.entities.RegUserRequestEntity
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import javax.inject.Inject


class ApiSignUpDataSource @Inject constructor(private val userService: UserService) {

    suspend fun signUpUser(signUpEntity: SignUpEntity): String? {
        val userResponseModel =
            RegUserRequestEntity(
                username = signUpEntity.username,
                password = signUpEntity.password,
                email = signUpEntity.email,
                birthday = signUpEntity.birthday,
                roles = listOf("User")
            )
        val userResponse = userService.createUser(userResponseModel)

        return if(userResponse.isSuccessful)
            null
        else {
            Gson().fromJson(
                userResponse.errorBody()!!.string(),
                ErrorUserResponse::class.java
            ).detail
        }
    }
}