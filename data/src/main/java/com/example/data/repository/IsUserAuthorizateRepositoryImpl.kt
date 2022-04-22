package com.example.data.repository

import com.example.data.api.models.ResponseLogin
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.managers.ApiTokenAccessManager
import javax.inject.Inject

class IsUserAuthorizateRepositoryImpl @Inject constructor(
    private val apiTokenAccessManager: ApiTokenAccessManager
) {
    suspend fun refreshUserAuthorizate(isUserExit: Boolean) {
        if(isUserExit)apiTokenAccessManager.Save().save(ResponseLogin("",""))
    }

    suspend fun isUserAuthorizate() = !apiTokenAccessManager.Read().read().hasEmpty()
}