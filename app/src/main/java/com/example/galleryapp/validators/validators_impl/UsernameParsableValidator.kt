package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.R
import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.SingleValidator
import com.example.galleryapp.validators.Validator

class UsernameParsableValidator(override val validateStr: String) : SingleValidator, ParsableCloudValidator {

    override val myErrorResponse: Int? = null
    override val errorTrigger: String = "username"
}