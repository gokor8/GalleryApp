package com.example.domain.core

interface IntoMapper {
    fun<I, R: IntoMapper> mapTo(inputModel: I) : R
}