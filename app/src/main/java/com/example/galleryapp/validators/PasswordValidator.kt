package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.R

class PasswordValidator : Validator {

    override val validateValue: Int? = null
    override val validationType: ValidationTypes = ValidationTypes.Password

    override fun validate(str: String): Int? {
        if (str.length < 5)
            return R.string.error_password

        return null
    }
}