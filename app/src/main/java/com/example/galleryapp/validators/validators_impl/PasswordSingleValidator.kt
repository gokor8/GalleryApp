package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.R
import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.SingleValidator

class PasswordSingleValidator(override val validateStr: String = "") : SingleValidator,
    ParsableCloudValidator {
    override val isNullData: Boolean
        get() = validateStr.isEmpty()

    override fun validate(): Int? {
        if (validateStr.length < 5)
            return R.string.error_password

        return null
    }

    override val myErrorResponse: Int? = null

    override val errorTrigger: String = "password"
}