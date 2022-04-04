package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryapp.ValidationHandler
import com.example.galleryapp.ValidatorStates

class SignUpFragmentViewModel : ViewModel() {

    val emailLiveData = MutableLiveData<Int?>()

    private val validatorHandler = ValidationHandler()

    fun validate(
        validateString: String,
        validatorType: ValidationHandler.ValidatorTypes,
        liveData: MutableLiveData<Int?>
    ) {
        if (validateString.isNotEmpty())
            validatorHandler.findValidator(validatorType).validate(validateString)
                .let(liveData::postValue)
    }

    fun trySignUp(): Boolean {
        // some todo realm
        return false
    }
}