package com.example.galleryapp.ui.fragments.auth

import com.example.domain.core.MapperTo
import com.example.domain.entities.auth.SignUpEntity

data class UiSignUpModel(
    val username: String,
    val email: String,
    val password: String,
    val birthday: String
) : MapperTo<SignUpEntity> {

    override fun mapTo(): SignUpEntity =
        SignUpEntity(
            this.username,
            this.password,
            if (birthday != "") {
                this.birthday
            } else {
                "01.01.2000"
            },
            this.email
        )
}