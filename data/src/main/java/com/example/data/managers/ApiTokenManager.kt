package com.example.data.managers

import com.example.data.datasource.CloudTokenDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.KeysEntity
import javax.inject.Inject

class ApiTokenManager @Inject constructor(
    private val cloudTokenDataSource: CloudTokenDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) {

    suspend fun getFreshToken(): KeysEntity {
        var token = sharedPreferencesDataSource.getKeys()
        if(token.clientId == null)
        { // TODO Шарпы
            token = cloudTokenDataSource.getNewToken().mapTo()
            sharedPreferencesDataSource.saveKeys(token)
        }
        /*else {
            val apiTokens = cloudTokenDataSource.getAvailableToken(token.clientId!!).mapTo()
            if(apiTokens != token)
                sharedPreferencesDataSource.saveKeys(apiTokens)
            token = apiTokens
        }*/

        return token
    }
}