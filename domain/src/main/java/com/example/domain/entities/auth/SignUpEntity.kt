package com.example.domain.entities.auth

class SignUpEntity(
    email: String,
    password: String,
    val birthday: String?,
    val username: String,
) : SignInEntity(username, password) {}