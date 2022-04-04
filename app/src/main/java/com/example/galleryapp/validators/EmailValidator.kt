package com.example.galleryapp.validators

import com.example.galleryapp.R
import com.example.galleryapp.ValidationFactory

class EmailValidator : Validator {

    override val validatorType = ValidationFactory.ValidatorTypes.Email

    override fun validate(str: String): Int? {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()

        return if (isValid)
            null
        else
            R.string.error_email
    }
}