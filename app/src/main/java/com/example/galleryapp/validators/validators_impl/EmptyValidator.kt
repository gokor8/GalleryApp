package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.validators.SingleValidator

class EmptyValidator(override val validateStr: String, override val errorMessage: String) :
    SingleValidator {
    // TODO Всегда ставить операторные скобки при if
    override fun validate(): String = if (validateStr.isEmpty()) errorMessage else ""
}