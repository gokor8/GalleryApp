package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.validators.ParsableCloudValidator
import com.example.galleryapp.validators.SingleValidator

class UsernameParsableValidator( val validateStr: String) : ParsableCloudValidator {

    override val myErrorResponse: Int? = null
    override val errorTrigger: String = "username"
}