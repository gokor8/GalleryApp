package com.example.galleryapp

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.multi_data_validators.MultiDataValidator
import javax.inject.Inject

class ValidationHandler @Inject constructor(
    private val validators: List<Validator>,
    private val multiDataValidators: List<MultiDataValidator>
) {

    fun findValidator(validationType: ValidationTypes) =
        validators.firstOrNull { validationType == it.validationType }

    fun findMultiDataValidator(validationType: ValidationTypes) =
        multiDataValidators.firstOrNull { validationType == it.validationType }
}