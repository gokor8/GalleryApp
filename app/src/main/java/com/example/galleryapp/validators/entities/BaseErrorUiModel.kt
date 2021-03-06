package com.example.galleryapp.validators.entities

import com.example.galleryapp.validators.Validator

data class BaseErrorUiModel(
    var errorMessage: String
) : ErrorUiModel {
    override val hasError = errorMessage != ""
}