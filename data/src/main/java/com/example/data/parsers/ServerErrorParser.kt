package com.example.data.parsers

import com.example.domain.core.ErrorType

interface ServerErrorParser {
    fun parse(errorMessage: String): Map<ErrorType, String>
}