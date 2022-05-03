package com.example.data.core.auth

import com.example.domain.core.ErrorContainer
import com.example.domain.core.ServerErrorType
import com.example.domain.core.MapperTo
import com.example.domain.entities.states.AuthState

sealed class AuthServerState : MapperTo<AuthState>{
    class ErrorData(private val serverErrorMap: Map<ServerErrorType, String>) : AuthServerState() {
        override fun mapTo(): AuthState = AuthState.Error(ErrorContainer(serverErrorMap))
    }

    class Success() : AuthServerState() {
        override fun mapTo(): AuthState = AuthState.Success()
    }
}