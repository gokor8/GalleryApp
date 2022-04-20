package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.RegistrationKeysModel
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class ApiSignUpDataSource @Inject constructor(
    private val userService: UserService,
    private val apiTokenRegistrationManager: ApiTokenRegistrationManager.Save,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignUpEntity, ResponseErrorSignUpModel>(
    serverErrorParser,
    gson,
    ResponseErrorSignUpModel::class.java
) {

    override suspend fun sendAuthRequest(signUpEntity: SignUpEntity): Response<*> {
        val userResponse = userService.createUser(RequestSignUpUserModel().mapTo(signUpEntity))

        if (!userResponse.isSuccessful) {
            return userResponse
        }

        apiTokenRegistrationManager.save(userResponse)

        return userResponse
    }
}