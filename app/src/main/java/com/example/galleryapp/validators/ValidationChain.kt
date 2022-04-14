package com.example.galleryapp.validators

class ValidationChain(
    private val begin: Validator,
    private val next: Validator
) : Validator {

    override fun validate(errorMessage: String): String {
        var errorMessage: String = ""

        begin.validate()
    }
}