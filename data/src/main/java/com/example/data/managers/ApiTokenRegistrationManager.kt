package com.example.data.managers

import com.example.data.api.models.ResponseRegistration
import com.example.data.datasource.auth.ApiTokenRegistrationDataSource
import com.example.data.storages.CacheSharedPreferences
import com.example.data.storages.models.RegistrationKeysModel
import javax.inject.Inject

class ApiTokenRegistrationManager @Inject constructor(
    private val apiTokenRegistrationDataSource: ApiTokenRegistrationDataSource,
    private val sharedPreferencesDataSource: CacheSharedPreferences.Mutable
) : ApiTokenManager {

    inner class Read : com.example.data.core.Read<RegistrationKeysModel> {
        override suspend fun read(): RegistrationKeysModel {
            val keys = sharedPreferencesDataSource.readKeys(
                listOf(
                    RegistrationKeysModel.CLIENT_ID,
                    RegistrationKeysModel.SECRET,
                    RegistrationKeysModel.RANDOM_ID
                )
            ).let(RegistrationKeysModel()::mapTo)

            return if (keys.hasEmpty()) {
                saveDefaultToken()
            } else {
                keys
            }
        }
    }

    inner class Save : ApiTokenManager.ReturnSave<ResponseRegistration, RegistrationKeysModel> {
        override suspend fun save(response: ResponseRegistration): RegistrationKeysModel {

            val tokenModel = apiTokenRegistrationDataSource.getUserToken(response.id).mapTo()

            if (tokenModel.hasEmpty()) {
                return saveDefaultToken()
            } else {
                save(tokenModel)
            }

            return tokenModel
        }
    }

    suspend fun saveDefaultToken(): RegistrationKeysModel {
        val keysModel = apiTokenRegistrationDataSource.getDefaultToken().mapTo()

        save(keysModel)

        return keysModel
    }

    private fun save(keysModel: RegistrationKeysModel) {
        mapOf(
            RegistrationKeysModel.CLIENT_ID to keysModel.clientId,
            RegistrationKeysModel.RANDOM_ID to keysModel.randomId,
            RegistrationKeysModel.SECRET to keysModel.secret
        ).let(sharedPreferencesDataSource::saveKeys)
    }
}