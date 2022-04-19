package com.example.data.managers

import com.example.data.datasource.ApiTokenDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.RegistrationKeysModel
import retrofit2.Response
import javax.inject.Inject

class ApiTokenManager @Inject constructor(
    private val apiTokenDataSource: ApiTokenDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) {

    /*suspend inline fun <R> sendWithToken(request: (tokens: RegistrationKeysModel) -> Response<R>): Response<R> {
        var tokens = getSavedToken()
        val response = request(tokens)

        if (response.code() != 200) {
            tokens = refreshToken()
            return request(tokens)
        }

        return response
    }

    suspend fun getSavedToken(): RegistrationKeysModel {
        var token = sharedPreferencesDataSource.getKeys()
        if (token.clientId == null) {
            token = apiTokenDataSource.getNewToken().mapTo()
            sharedPreferencesDataSource.saveKeys(token)
        }
        *//*else {
            val apiTokens = apiTokenDataSource.getUserToken(token.clientId!!).mapTo()
            if(apiTokens != token)
                sharedPreferencesDataSource.saveKeys(apiTokens)
            token = apiTokens
        }*//*

        return token
    }

    suspend fun refreshToken(): RegistrationKeysModel {
        var tokens = sharedPreferencesDataSource.getKeys()

        tokens.clientId?.let {
            val apiTokens = apiTokenDataSource.getUserToken(it).mapTo()
            sharedPreferencesDataSource.saveKeys(apiTokens)
            return apiTokens
        }

        return tokens
    }

    private suspend fun getFreshToken(clientId: String): RegistrationKeysModel {
        val apiTokens = apiTokenDataSource.getUserToken(clientId).mapTo()
        sharedPreferencesDataSource.saveKeys(apiTokens)

        return apiTokens
    }*/

}