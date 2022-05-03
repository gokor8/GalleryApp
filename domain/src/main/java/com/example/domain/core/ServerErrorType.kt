package com.example.domain.core

enum class ServerErrorType(val errorTrigger: String?) {
    Username("username"),
    Email("email"),
    Password("password")
}