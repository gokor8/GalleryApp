package com.example.galleryapp.validators.entities

// TODO check imports
import com.example.galleryapp.validators.Validator

data class ErrorEntity(
    var errorMessage: String
) {
    val hasError = errorMessage != ""
}