package com.example.galleryapp.ui.models

import com.example.domain.core.Mapper
import com.example.domain.entities.SignInEntity

data class UiSignInModel(
    val email: String,
    val password: String
) : Mapper<SignInEntity> {

    override fun mapTo(): SignInEntity =
        SignInEntity(email, password)
}