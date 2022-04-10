package com.example.galleryapp.ui_displays

import com.example.domain.core.ValidationTypes

open class BaseValidationParser {

    protected val baseErrorList = mapOf(
        ValidationTypes.Email to "This email already used",
        ValidationTypes.Username to "This username already used",
    )

    fun parse(error: String): Map<ValidationTypes, String> {
        val map = mutableMapOf<ValidationTypes, String>()

        val errors = error.split('.')
        for (validationType in baseErrorList) {
            validationType.key.errorTrigger?.let { trigger ->
                errors.firstOrNull {
                    it.contains(trigger)
                }?.apply {
                    map[validationType.key] = validationType.value
                    //map[validationType] = substringAfter(trigger)
                }
            }
        }

        return map
    }

}

/*fun parse(error: String): Map<ValidationTypes, String> {
        val map = mutableMapOf<ValidationTypes, String>()

        val errors = error.split('.')
        for (validationType in ValidationTypes.values()) {
            validationType.errorTrigger?.let { trigger ->
                errors.firstOrNull { it.contains(trigger) }?.apply {
                    baseErrorList.first { it.first == validationType }.second
                    //map[validationType] = substringAfter(trigger)
                }
            }
        }

        return map
    }*/