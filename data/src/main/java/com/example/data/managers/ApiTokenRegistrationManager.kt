package com.example.data.managers

import com.example.data.api.models.ResponseRegistration
import com.example.data.api.models.ResponseTokenModel
import com.example.data.datasource.ApiTokenRegistrationDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.RegistrationKeysModel
import retrofit2.Response
import javax.inject.Inject

class ApiTokenRegistrationManager @Inject constructor(
    private val apiTokenRegistrationDataSource: ApiTokenRegistrationDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : ApiTokenManager {

    inner class Read : ApiTokenManager.Read<RegistrationKeysModel> {
        override suspend fun read(): RegistrationKeysModel {
            val keys = sharedPreferencesDataSource.getKeys(
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

    inner class Save : ApiTokenManager.Save<ResponseRegistration> {
        override suspend fun save(response: Response<ResponseRegistration>) {

            val tokenModel: ResponseTokenModel? = response.body()?.let {
                apiTokenRegistrationDataSource.getUserToken(it.id)
            }
            if (tokenModel == null || tokenModel.hasEmpty()) {
                saveDefaultToken()
            } else {
                save(tokenModel.mapTo())
            }
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