package com.example.data.parsers

import android.content.Context
import com.example.domain.core.ErrorType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

open class BaseServerErrorParserImpl :
    ServerErrorParser {

    override fun parse(errorMessage: String): Map<ErrorType, String> {
        val map = mutableMapOf<ErrorType, String>()

        val errors = errorMessage.split('.')
        for (validator in ErrorType.values()) {
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