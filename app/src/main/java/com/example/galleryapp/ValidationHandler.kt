package com.example.galleryapp

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.validators.Validator
import javax.inject.Inject

class ValidationHandler @Inject constructor(private val validators: List<Validator>) {

    fun findValidator(validationType: ValidationTypes) =
        validators.first { validationType == it.validationType }
}