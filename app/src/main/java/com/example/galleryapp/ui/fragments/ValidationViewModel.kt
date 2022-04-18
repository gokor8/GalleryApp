package com.example.galleryapp.ui.fragments

import androidx.lifecycle.MutableLiveData
import com.example.domain.core.ErrorType
import com.example.domain.entities.AuthState
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.BaseErrorUiModel

interface ValidationViewModel {

    fun validate(
        validator: Validator,
        liveData: MutableLiveData<BaseErrorUiModel>
    ) {
        liveData.value = BaseErrorUiModel(validator.validate())
    }

    fun validate(
        validator: Validator,
        vararg liveDatas: MutableLiveData<BaseErrorUiModel>
    ) {
        for (liveData in liveDatas) {
            liveData.value = BaseErrorUiModel(validator.validate())
        }
    }

    fun validateAddError(
        validator: Validator,
        liveData: MutableLiveData<BaseErrorUiModel>
    ) {
        val lvValue = liveData.value
        if(lvValue != null)
            liveData.value = BaseErrorUiModel("${lvValue.errorMessage}\r\n${validator.validate()}")
        else
            liveData.value = BaseErrorUiModel(validator.validate())
    }

    fun mapAuthState(authState: AuthState)

    fun mapError(
        errorType: ErrorType,
        errorMessage: String
    ): Pair<MutableLiveData<BaseErrorUiModel>, String>?
}