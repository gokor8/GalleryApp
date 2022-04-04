package com.example.galleryapp.validators

import com.example.galleryapp.ValidationHandler

interface Validator {
    val validateValue: Int?
    val validatorType: ValidationHandler.ValidatorTypes

    fun validate(str: String): Int?
}