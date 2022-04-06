package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryapp.ValidationHandler

class SignUpFragmentViewModel : ViewModel() {

    val emailLiveData = MutableLiveData<Int?>()
    val birthdayLiveData = MutableLiveData<Int?>()

    private val validatorHandler = ValidationHandler()

    fun validate(
        str: String,
        validationType: ValidationHandler.ValidationTypes,
        postLiveData: MutableLiveData<Int?>
    ) {
        if (str.isNotEmpty())
            validatorHandler.findValidator(validationType).validate(str)
                .let(postLiveData::postValue)
    }

    fun trySignUp(): Boolean {
        // some todo realm
        return false
    }
}