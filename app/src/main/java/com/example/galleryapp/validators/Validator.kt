package com.example.galleryapp.validators

import com.example.galleryapp.ValidationFactory

interface Validator {

    val validatorType: ValidationFactory.ValidatorTypes

    fun validate(str: String): Int?
}