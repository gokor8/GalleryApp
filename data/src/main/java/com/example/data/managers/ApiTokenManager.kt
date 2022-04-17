package com.example.data.managers

import com.example.data.datasource.ApiTokenDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.KeysEntity
import javax.inject.Inject

class ApiTokenManager @Inject constructor(
    private val apiTokenDataSource: ApiTokenDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) {

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
        var clientId = sharedPreferencesDataSource.getKeys().clientId

        if(clientId != null) {
            val apiTokens = apiTokenDataSource.getAvailableToken(clientId).mapTo()
            sharedPreferencesDataSource.saveKeys(apiTokens)
            return apiTokens
        }
    }

}