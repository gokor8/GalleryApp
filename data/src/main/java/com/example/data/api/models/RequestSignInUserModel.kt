package com.example.data.api.models

import com.example.domain.core.MapperFrom
import com.example.domain.entities.SignInEntity

class RequestSignInUserModel(
    val id: String,
    val randomId: String,
    val secret: String,
    val email: String,
    val password: String
) : MapperFrom<SignInEntity, RequestSignInUserModel> {

    constructor(id: String, randomId: String, secret: String) : this(
        id,
        randomId,
        secret,
        email = "",
        password = ""
    )

    override fun mapTo(inputModel: SignInEntity): RequestSignInUserModel =
        RequestSignInUserModel(
            id,
            randomId,
            secret,
            email = inputModel.email,
            password = inputModel.password
        )

    fun toQueryMap(): Map<String, String> = mapOf(
        "client_id" to "${id}_${randomId}",
        "grant_type" to "password",
        "username" to email,
        "password" to password,
        "client_secret" to secret
    )
}