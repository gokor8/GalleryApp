package com.example.domain.entities

sealed class AuthState{

    class Success(val isSuccess: Boolean) : AuthState()
    class Error(val error: String) : AuthState()
    class Exception() : AuthState()
}
