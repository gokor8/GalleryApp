package com.example.galleryapp

import com.example.galleryapp.validators.EmailValidator

class ValidationFactory {

    private val validators = setOf(EmailValidator())

    enum class ValidatorTypes {
        Email, Password
    }

    fun create(validatorType: ValidatorTypes) =
        validators.first { validatorType == it.validatorType }
}