package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.managers.ApiTokenAccessManager
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.AccessTokensKeysModel
import com.example.domain.entities.SignInEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class ApiSignInDataSource @Inject constructor(
    private val userService: UserService,
    private val apiTokenRegistrationManager: ApiTokenRegistrationManager.Read,
    private val apiTokenAccessManager: ApiTokenAccessManager.Save,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignInEntity, ResponseErrorSignInModel>(
    serverErrorParser,
    gson,
    ResponseErrorSignInModel::class.java
) {

    override suspend fun sendAuthRequest(
        signInEntity: SignInEntity,
    ): Response<ResponseLogin> {

        val keys = apiTokenRegistrationManager.read()

        val userResponse = userService.loginUser(
            RequestSignInUserModel(
                id = keys.clientId,
                secret = keys.secret,
                randomId = keys.randomId
            ).mapTo(signInEntity).toQueryMap()
        )

        if(!userResponse.isSuccessful) {
            return userResponse
        }

        apiTokenAccessManager.save(userResponse)

        return userResponse
    }
}