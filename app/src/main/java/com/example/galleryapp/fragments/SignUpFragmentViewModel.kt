package com.example.galleryapp.fragments

import androidx.lifecycle.ViewModel
import com.example.galleryapp.ValidationFactory
import com.example.galleryapp.ValidatorStates

class SignUpFragmentViewModel : ViewModel() {

    private val validatorFactory = ValidationFactory()
    private val validateState = ValidatorStates(null, null)

    fun validate(validateString: String, validatorType: ValidationFactory.ValidatorTypes) {
        if (validateString.isNotEmpty())
            validatorType = validatorFactory.create(validatorType).validate(validateString)
    }

    fun trySignUp(): Boolean {
        // some todo realm
        return false
    }
}