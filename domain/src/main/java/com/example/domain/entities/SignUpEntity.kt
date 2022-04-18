package com.example.domain.entities

class SignUpEntity(
    email: String,
    password: String,
    val birthday: String?,
    val username: String,
) : SignInEntity(username, password) {}