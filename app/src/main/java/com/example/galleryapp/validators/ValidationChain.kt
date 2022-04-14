package com.example.galleryapp.validators

class ValidationChain(
    private val begin: Validator,
    private val next: Validator
) : Validator {

    override val errorMessage: String = ""

    override fun validate(): String {
        var errorMessage = ""

        errorMessage += buildErrorMessage(begin.validate())
        errorMessage += buildErrorMessage(next.validate())

        return errorMessage
    }

    private fun buildErrorMessage(errorMessage: String): String {
        return if(errorMessage != "")
             "$errorMessage\r\n"
        else
            errorMessage
    }
}