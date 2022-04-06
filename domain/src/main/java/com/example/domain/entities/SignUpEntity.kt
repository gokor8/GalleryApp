package com.example.domain.entities

class SignUpEntity(
    username: String,
    password: String,
    val birthday: String?,
    val email: String,
) : SignInEntity(username, password) {
}