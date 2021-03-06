package com.example.data.api.models.auth

import com.example.domain.core.MapperFrom
import com.example.domain.entities.auth.SignUpEntity

data class RequestSignUpUserModel(
    val email: String,
    val username: String,
    val password: String,
    val birthday: String?,
    val roles: List<String>,
) : MapperFrom<SignUpEntity, RequestSignUpUserModel> {

    constructor(): this("","","","01.01.2000", listOf())

    override fun mapTo(inputModel: SignUpEntity) =
        RequestSignUpUserModel(
            username = inputModel.username,
            password = inputModel.password,
            email = inputModel.email,
            birthday = inputModel.birthday,
            roles = listOf("User")
        )
}