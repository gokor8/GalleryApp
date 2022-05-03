package com.example.data.parsers

import com.example.domain.core.ServerErrorType

open class RegistrationServerErrorParserImpl :
    ServerErrorParser {

    override fun parse(errorMessage: String): Map<ServerErrorType, String> {
        val map = mutableMapOf<ServerErrorType, String>()

        val errors = errorMessage.split('.')
        for (validator in ServerErrorType.values()) {
            map[validator] = ""

            validator.errorTrigger?.let { trigger ->
                errors.firstOrNull {
                    it.contains(trigger)
                }?.apply {
                    map[validator] = substringAfter(trigger)
                }
            }
        }

        return map
    }

}