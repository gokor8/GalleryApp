package com.example.galleryapp

import com.example.galleryapp.validators.EmailValidator

class ValidationHandler {

    private val validators = setOf(EmailValidator())

    enum class ValidatorTypes {
        Email, Password
    }

    fun findValidator(validatorType: ValidatorTypes) =
        validators.first { validatorType == it.validatorType }
}