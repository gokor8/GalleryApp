package com.example.data.datasource.auth

import com.example.data.api.services.UserService
import com.example.data.api.models.auth.RequestSignUpUserModel
import com.example.data.api.models.auth.ResponseErrorSignUpModel
import com.example.data.api.models.auth.ResponseRegistration
import com.example.data.core.Read
import com.example.data.core.auth.AuthServerState
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.models.RegistrationKeysModel
import com.example.domain.entities.auth.SignUpEntity
import com.google.gson.Gson
import retrofit2.Response


class ApiSignUpDataSource constructor(
    private val userService: UserService,
    private val apiTokenRegistrationManager: ApiTokenRegistrationManager.Save,
    private val apiSignInDataSource: ApiSignInDataSource,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignUpEntity, ResponseErrorSignUpModel, ResponseRegistration>(
    serverErrorParser,
    gson,
    ResponseErrorSignUpModel::class.java
) {

    override suspend fun sendAuthRequest(authEntity: SignUpEntity): Response<ResponseRegistration> =
        userService.createUser(RequestSignUpUserModel().mapTo(authEntity))

    override suspend fun onSuccess(authEntity: SignUpEntity, responseModel: ResponseRegistration): AuthServerState {
        val registrationKeys = apiTokenRegistrationManager.save(responseModel)

        apiSignInDataSource.readRegistrationKeysModel = object : Read<RegistrationKeysModel> {
            override suspend fun read(): RegistrationKeysModel = registrationKeys
        }

        return apiSignInDataSource.getAuthorizationState(authEntity)
    }
}