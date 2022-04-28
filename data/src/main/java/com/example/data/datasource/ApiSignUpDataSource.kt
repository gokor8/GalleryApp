package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.core.Read
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.models.RegistrationKeysModel
import com.example.domain.entities.states.AuthState
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class ApiSignUpDataSource @Inject constructor(
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

    override suspend fun onSuccess(authEntity: SignUpEntity, responseModel: ResponseRegistration): AuthState {
        val registrationKeys = apiTokenRegistrationManager.save(responseModel)

        apiSignInDataSource.readRegistrationKeysModel = object : Read<RegistrationKeysModel> {
            override suspend fun read(): RegistrationKeysModel = registrationKeys
        }

        return apiSignInDataSource.getSignState(authEntity)
    }
}