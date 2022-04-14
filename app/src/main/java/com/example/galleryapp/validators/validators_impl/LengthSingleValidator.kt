package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.SingleValidator

class LengthSingleValidator(
    private val validationLength: Int, override val validateStr: String,
    override val errorMessage: String
) :
    SingleValidator, ParsableCloudValidator {

    override fun validate(): String {
        if (validateStr.length < validationLength)
            return errorMessage

        return ""
    }

    override val myErrorResponse: Int? = null

    override val errorTrigger: String = "password"
}