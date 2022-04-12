package com.example.galleryapp.ui_displays

import com.example.domain.core.ValidationTypes

open class BaseServerErrorParser {

    protected val baseErrorList = mapOf(
        ValidationTypes.Email to "This email already used",
        ValidationTypes.Username to "This username already used",
        ValidationTypes.Password to null
    )

    fun parse(error: String): Map<ValidationTypes, String> {
        val map = mutableMapOf<ValidationTypes, String>()

        val errors = error.split('.')
        for (validationType in baseErrorList) {
            map[validationType.key] = ""

            validationType.key.errorTrigger?.let { trigger ->
                errors.firstOrNull {
                    it.contains(trigger)
                }?.apply {
                    if(validationType.value != null)
                        map[validationType.key] = validationType.value!!
                    else
                        map[validationType.key] = substringAfter(trigger)
                }
            }
        }

        return map
    }

}