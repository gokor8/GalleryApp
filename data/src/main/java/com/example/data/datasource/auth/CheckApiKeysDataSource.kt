package com.example.data.datasource.auth

import com.example.data.core.auth.IsAuthUserState
import com.example.data.managers.ApiTokenAccessManager
import com.example.domain.entities.states.AuthState

class CheckApiKeysDataSource constructor(
    private val apiTokenSaveAccessManager: ApiTokenAccessManager.Save,
    private val apiTokenReadAccessManager: ApiTokenAccessManager.Read
) {
    suspend fun checkAndRefreshApiKeys(): IsAuthUserState {
        var accessKeys = apiTokenReadAccessManager.read()

        return if (accessKeys.hasEmpty()) IsAuthUserState.Error() else IsAuthUserState.Success(accessKeys)
    }
}