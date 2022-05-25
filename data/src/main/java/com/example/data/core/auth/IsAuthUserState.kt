package com.example.data.core.auth

import com.example.data.storages.models.AccessTokensKeysModel
import com.example.domain.core.MapperTo

sealed class IsAuthUserState {

    class Success(accessTokensKeysModel: AccessTokensKeysModel) : IsAuthUserState()

    class Error() : IsAuthUserState()
}