package com.example.domain.core

interface MapperFrom<I, R> {
    fun mapTo(inputModel: I) : R
}