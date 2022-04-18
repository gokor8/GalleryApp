package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.ErrorGsonParsableModel
import com.example.data.api.models.RequestSignUpUserModel
import com.example.data.api.models.ResponseErrorSignUpModel
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.domain.core.Mapper
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson

abstract class BaseApiAuthDataSource<I : Mapper<RequestSignUpUserModel>, R>(
    protected val userService: I,
    protected val serverErrorParser: ServerErrorParser,
    protected val gson: Gson
) {

    suspend fun signUpUser(responseModel: I): AuthState {
       /* val userResponseModel =
            RequestSignUpUserModel(
                username = signUpEntity.username,
                password = signUpEntity.password,
                email = signUpEntity.email,
                birthday = signUpEntity.birthday,
                roles = listOf("User")
            )*/
        //val userResponse = userService.createUser(responseModel)

        return RetrofitResponseManager(
            serverErrorParser,
            ErrorGsonParsableModel(gson, ResponseErrorSignUpModel::class.java)
        ).getAuthState(userResponse)
    }
}