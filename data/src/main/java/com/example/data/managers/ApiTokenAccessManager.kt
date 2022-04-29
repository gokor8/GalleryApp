package com.example.data.managers

import com.example.data.api.models.auth.ResponseLogin
import com.example.data.storages.models.AccessTokensKeysModel
import com.example.data.storages.CacheSharedPreferences
import com.example.data.storages.models.RegistrationKeysModel

class ApiTokenAccessManager(private val sharedPreferencesDataSource: CacheSharedPreferences.Mutable) :
    ApiTokenManager {

    inner class Read : com.example.data.core.Read<AccessTokensKeysModel> {
        override suspend fun read(): AccessTokensKeysModel {
            val keys = sharedPreferencesDataSource.readKeys(
                listOf(
                    RegistrationKeysModel.CLIENT_ID,
                    RegistrationKeysModel.SECRET,
                    RegistrationKeysModel.RANDOM_ID
                )
            ).let(AccessTokensKeysModel()::mapTo)

            /*if (keys.hasEmpty()) {
                throw Exception("Empty access keys in shared preferences")
            }*/

            return keys
        }
    }

    inner class Save : ApiTokenManager.Save<ResponseLogin> {
        override suspend fun save(response: ResponseLogin) {
            response.mapTo().also {
                mapOf(
                    AccessTokensKeysModel.ACCESS_TOKEN to it.accessToken,
                    AccessTokensKeysModel.REFRESH_TOKEN to it.refreshToken
                ).let(sharedPreferencesDataSource::saveKeys)
            }
        }
    }
}