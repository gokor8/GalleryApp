package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.validators.SingleValidator

class EmptyValidator(override val validateStr: String, override val errorMessage: String) :
    SingleValidator {
    override fun validate(): String = if (validateStr.isEmpty()) errorMessage else ""
}