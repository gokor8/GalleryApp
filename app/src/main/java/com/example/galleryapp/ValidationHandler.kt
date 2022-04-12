package com.example.galleryapp

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.Validators
import javax.inject.Inject

class ValidationHandler @Inject constructor(
    private val validators: List<Validators>,
) {

    fun findValidator(validator: Validators) =
        validators.firstOrNull { validator == it }

}