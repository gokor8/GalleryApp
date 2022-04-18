package com.example.galleryapp.ui.fragments

import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.ErrorUiModel

interface ValidationViewModel {

    fun validate(
        validator: Validator,
        liveData: MutableLiveData<ErrorUiModel>
    ) {
        liveData.value = ErrorUiModel(validator.validate())
    }

    fun validate(
        validator: Validator,
        vararg liveDatas: MutableLiveData<ErrorUiModel>
    ) {
        for (liveData in liveDatas) {
            liveData.value = ErrorUiModel(validator.validate())
        }
    }

    fun validateAddError(
        validator: Validator,
        liveData: MutableLiveData<ErrorUiModel>
    ) {
        val lvValue = liveData.value
        if(lvValue != null)
            liveData.value = ErrorUiModel("${lvValue.errorMessage}\r\n${validator.validate()}")
        else
            liveData.value = ErrorUiModel(validator.validate())
    }
}