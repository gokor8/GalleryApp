package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.entities.ErrorUserResponse
import com.example.data.api.entities.RegUserRequestEntity
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import javax.inject.Inject


class CloudAuthDataSource @Inject constructor(
    private val userService: UserService,
    private val serverErrorParser: ServerErrorParser
) {

    suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState {
        val userResponseModel =
            RegUserRequestEntity(
                username = signUpEntity.username,
                password = signUpEntity.password,
                email = signUpEntity.email,
                birthday = signUpEntity.birthday,
                roles = listOf("User")
            )
        val userResponse = userService.createUser(userResponseModel)

        return RetrofitResponseManager(serverErrorParser).getAuthState(userResponse)
    }
}