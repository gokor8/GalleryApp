package com.example.galleryapp.validators.entities

import com.example.galleryapp.validators.Validator

data class ErrorEntity(
    private val errorMessage: String?
) {
    val hasError = errorMessage != null
}