package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.R
import com.example.galleryapp.ValidationHandler

class EmailValidator(override val validateStr: String = "") : Validators(), Validator, ParsebleCloudValidator {

    override val isNullData: Boolean
        get() = validateStr.isEmpty()

    override fun validate(): Int? {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(validateStr).matches()

        return if (isValid)
            null
        else
            R.string.error_email
    }

    override val errorTrigger: String = "email"
}