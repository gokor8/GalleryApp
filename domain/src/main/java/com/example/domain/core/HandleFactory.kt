package com.example.domain.core

interface HandleFactory<E> {
    fun handle(e: E): UiFailModel
}