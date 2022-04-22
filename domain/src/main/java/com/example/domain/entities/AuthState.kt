package com.example.domain.entities

import com.example.domain.core.ErrorType
import com.example.domain.core.UiException

sealed class AuthState{

    class Success(val isSuccess: Boolean) : AuthState()
    class Error(val errorMap: Map<ErrorType, String>) : AuthState()
    class Exception(val exception: UiException) : AuthState()
}
