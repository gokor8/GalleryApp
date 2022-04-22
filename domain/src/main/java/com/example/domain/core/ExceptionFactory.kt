package com.example.domain.core

import java.lang.Exception

interface ExceptionFactory {
    fun handle(e: Exception): UiException
}