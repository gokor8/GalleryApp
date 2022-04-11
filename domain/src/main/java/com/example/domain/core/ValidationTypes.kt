package com.example.domain.core

enum class ValidationTypes(val errorTrigger: String?) {
    Username("username"),Email("email"),Password("password"), Date(null)
}