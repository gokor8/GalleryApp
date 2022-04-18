package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.parsers.ServerErrorParser
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class ApiSignInDataSource @Inject constructor(
    private val userService: UserService,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignInEntity, ResponseErrorSignInModel>(
    serverErrorParser,
    gson,
    ResponseErrorSignInModel::class.java
) {

    override suspend fun sendAuthRequest(signInEntity: SignInEntity): Response<ResponseTokenModel> =
        userService.loginUser(RequestSignInUserModel().mapTo(signInEntity))
}