package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.core.Read
import com.example.data.managers.ApiTokenAccessManager
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.managers.RetrofitErrorManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.models.RegistrationKeysModel
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class ApiSignInDataSource @Inject constructor(
    private val userService: UserService,
    var readRegistrationKeysModel: Read<RegistrationKeysModel>,
    private val apiTokenAccessManager: ApiTokenAccessManager.Save,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignInEntity, ResponseErrorSignInModel, ResponseLogin>(
    serverErrorParser,
    gson,
    ResponseErrorSignInModel::class.java
) {

    override suspend fun sendAuthRequest(
        authEntity: SignInEntity,
    ): Response<ResponseLogin> {

        val keys = readRegistrationKeysModel.read()

        val userResponse = userService.loginUser(
            RequestSignInUserModel(
                id = keys.clientId,
                secret = keys.secret,
                randomId = keys.randomId
            ).mapTo(authEntity).toQueryMap()
        )

        userResponse.body()?.also {
            apiTokenAccessManager.save(it)
        }

        return userResponse
    }
}