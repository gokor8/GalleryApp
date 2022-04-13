package com.example.galleryapp.validators.entities

import com.example.galleryapp.validators.Validator

data class ErrorEntity(
    val errorId: ArrayList<Int?>
) {
    val hasError = errorId.isNotEmpty()

    fun clearErrors() {
        errorId.clear()
    }
}