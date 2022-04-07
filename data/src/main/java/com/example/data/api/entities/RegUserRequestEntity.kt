package com.example.data.api.entities

data class RegUserRequestEntity(
    val email: String,
    val username: String,
    val password: String,
    val birthday: String?,
    val roles: List<String>,
)