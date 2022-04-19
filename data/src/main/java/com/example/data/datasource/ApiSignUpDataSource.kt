package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.api.models.*
import com.example.data.managers.RetrofitResponseManager
import com.example.data.parsers.ServerErrorParser
import com.example.data.storages.RegistrationKeysModel
import com.example.domain.entities.AuthState
import com.example.domain.entities.SignInEntity
import com.example.domain.entities.SignUpEntity
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class ApiSignUpDataSource @Inject constructor(
    private val userService: UserService,
    private val apiTokenDataSource: ApiTokenDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
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

        val responseModel = userResponse.body()!!
        val tokenModel = apiTokenDataSource.getUserToken(responseModel.id).mapTo()

        mapOf(
            RegistrationKeysModel.CLIENT_ID to tokenModel.clientId,
            RegistrationKeysModel.RANDOM_ID to tokenModel.randomId,
            RegistrationKeysModel.SECRET to tokenModel.secret
        ).let (sharedPreferencesDataSource::saveKeys)

        return userResponse
    }
}