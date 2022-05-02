package com.example.domain.core

interface HandleFactory<E, R> {
    fun handle(e: E): R
}