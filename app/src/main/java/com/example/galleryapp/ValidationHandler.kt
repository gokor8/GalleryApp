package com.example.galleryapp

import com.example.galleryapp.validators.DateValidator
import com.example.galleryapp.validators.EmailValidator

class ValidationHandler {

    private val validators = setOf(EmailValidator(), DateValidator())

    fun findValidator(validationType: ValidationTypes) =
        validators.first { validationType == it.validationType }

    enum class ValidationTypes {
        Email, Date
    }
}