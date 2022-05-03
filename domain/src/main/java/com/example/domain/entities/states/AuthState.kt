package com.example.domain.entities.states

import com.example.domain.core.ErrorContainer
import com.example.domain.core.UiFailModel

sealed class AuthState : ReturnState() {
    class Success : AuthState(), ReturnState.Success
    class Error(val errorsContainer: ErrorContainer) : AuthState(), ReturnState.Error
    class Exception(val failModel: UiFailModel) : AuthState()
}
