package com.example.domain.entities.states

import com.example.domain.core.ErrorType
import com.example.domain.core.UiException
import com.example.domain.entities.states.ReturnState

sealed class AuthState : ReturnState() {
    class Success() : AuthState(), ReturnState.Success
    class Error(val errorMap: Map<ErrorType, String>) : AuthState(), ReturnState.Error
    class Exception(val exception: UiException) : AuthState()
}
