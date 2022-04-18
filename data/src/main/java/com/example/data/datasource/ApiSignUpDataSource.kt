package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.ErrorGsonParsableModel
import com.example.data.api.models.RequestSignUpUserModel
import com.example.data.api.models.ResponseErrorSignUpModel
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import javax.inject.Inject


class ApiSignUpDataSource @Inject constructor(
    private val userService: UserService,
    private val serverErrorParser: ServerErrorParser,
    private val gson: Gson
) {

    suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState {
        val userResponseModel =
            RequestSignUpUserModel(
                username = signUpEntity.username,
                password = signUpEntity.password,
                email = signUpEntity.email,
                birthday = signUpEntity.birthday,
                roles = listOf("User")
            )
        val userResponse = userService.createUser(userResponseModel)

        return RetrofitResponseManager(
            serverErrorParser,
            ErrorGsonParsableModel(gson, ResponseErrorSignUpModel::class.java)
        ).getAuthState(userResponse)
    }
}