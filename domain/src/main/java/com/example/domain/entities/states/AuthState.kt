package com.example.domain.entities.states

import com.example.domain.core.ErrorContainer
import com.example.domain.core.UiFailModel

sealed class AuthState : DomainState() {
    class Success : AuthState(), DomainState.Success
    class Error(val errorsContainer: ErrorContainer) : AuthState(), DomainState.Error
    class Exception(val failModel: UiFailModel) : AuthState(), DomainState.Exception
}
