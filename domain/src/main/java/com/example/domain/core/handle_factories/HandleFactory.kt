package com.example.domain.core.handle_factories

interface HandleFactory<E, R> {
    fun handle(e: E): R
}