package com.example.domain.core

enum class ErrorType(val errorTrigger: String?) {
    Username("username"),
    Email("email"),
    Password("password")
}