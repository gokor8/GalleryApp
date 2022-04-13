package com.example.galleryapp

import com.example.galleryapp.validators.Validator
import javax.inject.Inject

class ValidatorsHandler @Inject constructor(
    private val validators: List<Validator>,
) {

    fun findValidator(validator: Validator) =
        validators.firstOrNull { validator == it }

}