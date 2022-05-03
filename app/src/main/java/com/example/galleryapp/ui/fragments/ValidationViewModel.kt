package com.example.galleryapp.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.core.ServerErrorType
import com.example.domain.entities.states.AuthState
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.BaseErrorUiModel
import com.example.galleryapp.validators.entities.ErrorUiModel

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
        serverErrorType: ServerErrorType,
        errorMessage: String
    ): Pair<MutableLiveData<BaseErrorUiModel>, String>?

    fun<E : ErrorUiModel> checkingErrors(listLiveDates: List<LiveData<E>>): Boolean {
        var withoutErrors = true

        listLiveDates.forEach {
            if (it.value == null) {
                withoutErrors = false
            } else if (it.value!!.hasError) {
                withoutErrors = false
            }
        }

        return withoutErrors
    }
}