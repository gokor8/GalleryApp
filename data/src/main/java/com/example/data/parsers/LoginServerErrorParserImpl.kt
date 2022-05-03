package com.example.data.parsers

import com.example.domain.core.ServerErrorType

class LoginServerErrorParserImpl : ServerErrorParser {
    override fun parse(errorMessage: String): Map<ServerErrorType, String> {
        val map = mutableMapOf<ServerErrorType, String>()

        map[ServerErrorType.Email] = errorMessage
        map[ServerErrorType.Password] = errorMessage

        return map
    }
}