package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.validators.entities.ErrorEntity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface FocusValidationFragment {

    var lastValidationField: TextInputEditText?

    fun TextInputEditText.setBaseOnFocusChangeListener(
        postedliveData: MutableLiveData<ErrorEntity>,
        otherLogic: (postedliveData: MutableLiveData<ErrorEntity>) -> Unit
    ) {
        this.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                lastValidationField = this
                return@setOnFocusChangeListener
            }

            otherLogic(postedliveData)
        }
    }

    fun setError(textInputLayout: TextInputLayout, errorEntity: ErrorEntity) {
        textInputLayout.error = errorEntity.errorMessage
        textInputLayout.isErrorEnabled = errorEntity.hasError
    }
}