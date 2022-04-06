package com.example.galleryapp.validators

import com.example.galleryapp.ValidationHandler

interface Validator {
    val validateValue: Int?
    val validationType: ValidationHandler.ValidationTypes

    fun validate(str: String): Int?
}