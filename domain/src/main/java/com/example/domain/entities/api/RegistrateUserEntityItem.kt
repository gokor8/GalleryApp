package com.example.domain.entities.api

data class RegistrateUserEntityItem(
    val email: String,
    val username: String,
    val password: String,
    //val birthday: String?,
    val roles: List<String>,
)