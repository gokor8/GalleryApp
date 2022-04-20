package com.example.data.managers

import com.example.data.datasource.ApiTokenRegistrationDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import javax.inject.Inject

class ApiTokenManagerBabijon @Inject constructor(
    private val apiTokenRegistrationDataSource: ApiTokenRegistrationDataSource,
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
            token = apiTokenRegistrationDataSource.getNewToken().mapTo()
            sharedPreferencesDataSource.saveKeys(token)
        }
        *//*else {
            val apiTokens = apiTokenRegistrationDataSource.getUserToken(token.clientId!!).mapTo()
            if(apiTokens != token)
                sharedPreferencesDataSource.saveKeys(apiTokens)
            token = apiTokens
        }*//*

        return token
    }

    suspend fun refreshToken(): RegistrationKeysModel {
        var tokens = sharedPreferencesDataSource.getKeys()

        tokens.clientId?.let {
            val apiTokens = apiTokenRegistrationDataSource.getUserToken(it).mapTo()
            sharedPreferencesDataSource.saveKeys(apiTokens)
            return apiTokens
        }

        return tokens
    }

    private suspend fun getFreshToken(clientId: String): RegistrationKeysModel {
        val apiTokens = apiTokenRegistrationDataSource.getUserToken(clientId).mapTo()
        sharedPreferencesDataSource.saveKeys(apiTokens)

        return apiTokens
    }*/

}