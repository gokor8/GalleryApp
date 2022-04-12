package com.example.galleryapp.ui_displays

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.validators.EmailValidator
import com.example.galleryapp.validators.ParsebleCloudValidator
import com.example.galleryapp.validators.PasswordValidator

open class BaseServerErrorParser {

    protected val parsebleCloudValidatorы: List<ParsebleCloudValidator> = listOf(
        EmailValidator(""), PasswordValidator("")
    )

    fun parse(error: String): Map<ParsebleCloudValidator, String> {
        val map = mutableMapOf<ParsebleCloudValidator, String>()

        val errors = error.split('.')
        for (validator in parsebleCloudValidatorы) {
            map[validator] = ""

            validator.errorTrigger?.let { trigger ->
                errors.firstOrNull {
                    it.contains(trigger)
                }?.apply {
                    if(validator.errorTrigger != null)
                        map[validator] = validator.errorTrigger!!
                    else
                        map[validator] = substringAfter(trigger)
                }
            }
        }

        return map
    }

}