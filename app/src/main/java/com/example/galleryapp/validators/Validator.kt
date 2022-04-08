package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.ValidationHandler

interface Validator {
    val validateValue: Int?
    val validationType: ValidationTypes

    fun validate(str: String): Int?
}