package com.example.domain.entities

import com.example.domain.core.ErrorType

sealed class AuthState{

    class Success(val isSuccess: Boolean) : AuthState()
    class Error(val errorMap: Map<ErrorType, String>) : AuthState()
    class Exception(val errorMessage: String) : AuthState()
}
