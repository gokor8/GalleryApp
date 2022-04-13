package com.example.galleryapp.validators.entities

import com.example.galleryapp.validators.Validator

data class ErrorEntity(
    val errorMessage: String
) {
    val hasError = errorMessage.isNotEmpty()
}