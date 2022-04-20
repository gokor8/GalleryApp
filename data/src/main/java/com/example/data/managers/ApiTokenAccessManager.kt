package com.example.data.managers

import com.example.data.api.models.ResponseLogin
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.AccessTokensKeysModel
import com.example.data.storages.RegistrationKeysModel
import retrofit2.Response

class ApiTokenAccessManager(private val sharedPreferencesDataSource: SharedPreferencesDataSource) :
    ApiTokenManager {

    inner class Read : ApiTokenManager.Read<AccessTokensKeysModel> {
        override suspend fun read(): AccessTokensKeysModel {
            val keys = sharedPreferencesDataSource.getKeys(
                listOf(
                    RegistrationKeysModel.CLIENT_ID,
                    RegistrationKeysModel.SECRET,
                    RegistrationKeysModel.RANDOM_ID
                )
            ).let(AccessTokensKeysModel()::mapTo)

            if (keys.hasEmpty()) {
                throw Exception("Empty access keys in shared preferences")
            }

            return keys
        }
    }

    inner class Save : ApiTokenManager.Save<ResponseLogin> {
        override suspend fun save(response: Response<ResponseLogin>) {
            response.body()?.mapTo()?.also {
                mapOf(
                    AccessTokensKeysModel.ACCESS_TOKEN to it.accessToken,
                    AccessTokensKeysModel.REFRESH_TOKEN to it.refreshToken
                ).let(sharedPreferencesDataSource::saveKeys)
            }
        }
    }
}