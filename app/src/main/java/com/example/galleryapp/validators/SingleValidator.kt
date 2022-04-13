package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes

interface SingleValidator : Validator {
    val validateStr: String

    override val isNullData: Boolean
        get() = validateStr.isEmpty()
}