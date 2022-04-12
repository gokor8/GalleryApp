package com.example.galleryapp.ui_displays

import com.example.domain.core.Mapper
import com.example.domain.entities.SignUpEntity

data class UISignUpEntity(
    val username: String,
    val email: String,
    val password: String,
    val birthday: String?
) : Mapper<SignUpEntity> {
    override fun mapTo(): SignUpEntity =
        SignUpEntity(
            this.username,
            this.password,
            this.birthday,
            this.email
        )
}