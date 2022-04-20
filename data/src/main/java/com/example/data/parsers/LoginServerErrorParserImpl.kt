package com.example.data.parsers

import com.example.domain.core.ErrorType

class LoginServerErrorParserImpl : ServerErrorParser {
    override fun parse(errorMessage: String): Map<ErrorType, String> {
        val map = mutableMapOf<ErrorType, String>()

        map[ErrorType.Email] = errorMessage
        map[ErrorType.Password] = errorMessage

        return map
    }
}