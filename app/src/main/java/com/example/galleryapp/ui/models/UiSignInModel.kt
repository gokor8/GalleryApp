package com.example.galleryapp.ui.models

import com.example.domain.core.MapperTo
import com.example.domain.entities.auth.SignInEntity

data class UiSignInModel(
    val email: String,
    val password: String
) : MapperTo<SignInEntity> {

    override fun mapTo(): SignInEntity =
        SignInEntity(email, password)
}