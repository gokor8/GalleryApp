package com.example.galleryapp.ui.fragments

import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.validators.entities.BaseErrorUiModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface FocusValidationFragment {

    var lastValidationField: TextInputEditText?

    fun TextInputEditText.setBaseOnFocusChangeListener(
        postedliveData: MutableLiveData<BaseErrorUiModel>,
        otherLogic: (postedliveData: MutableLiveData<BaseErrorUiModel>) -> Unit
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
        postedliveData: MutableLiveData<BaseErrorUiModel>
    ) {
        this.setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                lastValidationField = this
                return@setOnFocusChangeListener
            }
        }
    }

    fun setError(textInputLayout: TextInputLayout, baseErrorUiModel: BaseErrorUiModel) {
        textInputLayout.error = baseErrorUiModel.errorMessage
        textInputLayout.isErrorEnabled = baseErrorUiModel.hasError
    }
}