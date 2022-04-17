package com.example.data.api.models

data class RequestSignUpUserModel(
    val email: String,
    val username: String,
    val password: String,
    val birthday: String?,
    val roles: List<String>,
)