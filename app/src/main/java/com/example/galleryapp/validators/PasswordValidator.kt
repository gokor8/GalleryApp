package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.R

class PasswordValidator(override val validateStr: String = "") : Validators(), Validator, ParsebleCloudValidator {
    override val isNullData: Boolean
        get() = validateStr.isEmpty()

    override fun validate(): Int? {
        if (validateStr.length < 5)
            return R.string.error_password

        return null
    }

    override val errorTrigger: String = "password"
}