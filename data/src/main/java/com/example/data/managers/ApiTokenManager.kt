package com.example.data.managers

import com.example.data.datasource.ApiTokenDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.KeysEntity
import retrofit2.Response
import javax.inject.Inject

class ApiTokenManager @Inject constructor(
    private val apiTokenDataSource: ApiTokenDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) {

    suspend inline fun<R> sendWithToken(request: (tokens: KeysEntity) -> Response<R>): Response<R> {
        var tokens = getSavedToken()
        val response = request(tokens)

        if(response.code() == 400) {
            tokens = refreshToken()
            return request(tokens)
        }

        return response
    }

    suspend fun getSavedToken(): KeysEntity {
        var token = sharedPreferencesDataSource.getKeys()
        if(token.clientId == null)
        {
            token = apiTokenDataSource.getNewToken().mapTo()
            sharedPreferencesDataSource.saveKeys(token)
        }
        /*else {
            val apiTokens = apiTokenDataSource.getAvailableToken(token.clientId!!).mapTo()
            if(apiTokens != token)
                sharedPreferencesDataSource.saveKeys(apiTokens)
            token = apiTokens
        }*/

        return token
    }

    suspend fun refreshToken(): KeysEntity {
        var tokens = sharedPreferencesDataSource.getKeys()

        tokens.clientId?.let {
            val apiTokens = apiTokenDataSource.getAvailableToken(it).mapTo()
            sharedPreferencesDataSource.saveKeys(apiTokens)
            return apiTokens
        }

        return tokens
    }

}