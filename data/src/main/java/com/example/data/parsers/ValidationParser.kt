package com.example.data.parsers

import com.example.domain.core.ValidationTypes

class ValidationParser {

    companion object {
        fun parse(error: String): Map<ValidationTypes, String> {
            val map = mutableMapOf<ValidationTypes, String>()

            val errors = error.split('.')
            for (validationType in ValidationTypes.values()) {
                validationType.errorTrigger?.let { trigger ->
                    errors.firstOrNull{ it.contains(trigger) }?.apply {
                        map[validationType] = substringAfter(trigger)
                    }
                }
            }

            return map
        }
    }
}