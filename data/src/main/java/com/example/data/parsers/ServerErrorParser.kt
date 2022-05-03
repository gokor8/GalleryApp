package com.example.data.parsers

import com.example.domain.core.ServerErrorType

interface ServerErrorParser {
    fun parse(errorMessage: String): Map<ServerErrorType, String>
}