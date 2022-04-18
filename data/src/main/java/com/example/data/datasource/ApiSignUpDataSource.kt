package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.ErrorGsonParsableModel
import com.example.data.api.models.RequestSignInUserModel
import com.example.data.api.models.RequestSignUpUserModel
import com.example.data.api.models.ResponseErrorSignUpModel
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class ApiSignUpDataSource @Inject constructor(
    private val userService: UserService,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignUpEntity, ResponseErrorSignUpModel>(
    serverErrorParser,
    gson,
    ResponseErrorSignUpModel::class.java
) {

    /*suspend fun signUpUser(signUpEntity: SignUpEntity): AuthState {
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
    }*/

    override suspend fun sendAuthRequest(signUpEntity: SignUpEntity): Response<Any?> =
        userService.createUser(RequestSignUpUserModel().mapTo(signUpEntity))
}