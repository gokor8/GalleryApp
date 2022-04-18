package com.example.galleryapp.ui.fragments

import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.validators.entities.ErrorUiModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface FocusValidationFragment {

    var lastValidationField: TextInputEditText?

    fun TextInputEditText.setBaseOnFocusChangeListener(
        postedliveData: MutableLiveData<ErrorUiModel>,
        otherLogic: (postedliveData: MutableLiveData<ErrorUiModel>) -> Unit
    ) {
        this.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                lastValidationField = this
                return@setOnFocusChangeListener
            }

            otherLogic(postedliveData)
        }
    }

    fun TextInputEditText.setBaseOnFocusChangeListener(
        postedliveData: MutableLiveData<ErrorUiModel>
    ) {
        this.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                lastValidationField = this
                return@setOnFocusChangeListener
            }
        }
    }

    fun setError(textInputLayout: TextInputLayout, errorUiModel: ErrorUiModel) {
        textInputLayout.error = errorUiModel.errorMessage
        textInputLayout.isErrorEnabled = errorUiModel.hasError
    }
}