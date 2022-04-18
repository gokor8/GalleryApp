package com.example.data.api.models

import com.example.domain.core.IntoMapper
import com.example.domain.core.Mapper
import com.example.domain.entities.SignInEntity

data class RequestSignUpUserModel(
    val email: String,
    val username: String,
    val password: String,
    val birthday: String?,
    val roles: List<String>,
) : IntoMapper {
    override fun <I, R : IntoMapper> mapTo(inputModel: I): R {
        TODO("Not yet implemented")
    }
    override fun<I: SignInEntity, R: RequestSignUpUserModel> mapTo(inputModel: I): R {

    }
}