package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.R
import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.SingleValidator

class EmailSingleValidator(
    override val validateStr: String = "",
    override val errorMessage: String
) : SingleValidator,
    ParsableCloudValidator {

    override fun validate(): String {
        val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(validateStr).matches()

        return if (isValid)
            ""
        else
            errorMessage
    }

    override val myErrorResponse: Int = R.string.cloud_error_email

    override val errorTrigger: String = "email"
}