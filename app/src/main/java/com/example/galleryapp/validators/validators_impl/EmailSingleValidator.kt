package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.R
import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.SingleValidator

class EmailSingleValidator(override val validateStr: String = "") : SingleValidator,
    ParsableCloudValidator {

    override val isNullData: Boolean
        get() = validateStr.isEmpty()

    override fun validate(): Int? {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(validateStr).matches()

        return if (isValid)
            null
        else
            R.string.error_email
    }

    override val myErrorResponse: Int = R.string.cloud_error_email

    override val errorTrigger: String = "email"
}