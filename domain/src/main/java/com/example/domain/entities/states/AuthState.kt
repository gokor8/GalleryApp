package com.example.domain.entities.states

import com.example.domain.core.ErrorType
import com.example.domain.core.UiFailModel

sealed class AuthState : ReturnState() {
    class Success() : AuthState(), ReturnState.Success
    class Error(val errorMap: Map<ErrorType, String>) : AuthState(), ReturnState.Error
    class Exception(val failModel: UiFailModel) : AuthState()
}
