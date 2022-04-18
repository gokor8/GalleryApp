package com.example.data.api.models

import com.example.domain.core.MapperFrom
import com.example.domain.entities.SignInEntity

class RequestSignInUserModel(
    val email: String,
    val password: String
) : MapperFrom<SignInEntity, RequestSignInUserModel> {

    constructor() : this("","")

    override fun mapTo(inputModel: SignInEntity): RequestSignInUserModel =
        RequestSignInUserModel(
            email = inputModel.email,
            password = inputModel.password
        )
}