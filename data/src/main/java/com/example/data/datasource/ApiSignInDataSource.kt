package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.ClientKeysModel
import com.example.data.storages.RegistrationKeysModel
import com.example.domain.entities.SignInEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class ApiSignInDataSource @Inject constructor(
    private val userService: UserService,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    serverErrorParser: ServerErrorParser,
    gson: Gson
) : BaseApiAuthDataSource<SignInEntity, ResponseErrorSignInModel>(
    serverErrorParser,
    gson,
    ResponseErrorSignInModel::class.java
) {

    override suspend fun sendAuthRequest(
        signInEntity: SignInEntity,
    ): Response<ResponseLogining> {

        val keys = sharedPreferencesDataSource.getKeys(
            listOf(
                RegistrationKeysModel.CLIENT_ID,
                RegistrationKeysModel.SECRET,
                RegistrationKeysModel.RANDOM_ID
            )
        ).let(RegistrationKeysModel()::mapTo)

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

        val responseLogining = userResponse.body()!!

        mapOf(
            ClientKeysModel.ACCESS_TOKEN to responseLogining.accessToken,
            ClientKeysModel.REFRESH_TOKEN to responseLogining.refreshToken
        ).let(sharedPreferencesDataSource::saveKeys)

        return userResponse
    }
}