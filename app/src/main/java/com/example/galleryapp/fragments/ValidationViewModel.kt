package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.ErrorEntity

interface ValidationViewModel {

    fun validate(
        validator: Validator,
        liveData: MutableLiveData<ErrorEntity>
    ) {
        ErrorEntity(validator.validate()).let(liveData::postValue)
    }

    fun validate(
        validator: Validator,
        vararg liveDatas: MutableLiveData<ErrorEntity>
    ) {
        for (liveData in liveDatas) {
            ErrorEntity(validator.validate()).let(liveData::postValue)
        }
    }

    fun validateAddError(
        validator: Validator,
        liveData: MutableLiveData<ErrorEntity>
    ) {
        if(liveData.value != null)
            liveData.value!!.errorMessage += validator.validate()
        else
            ErrorEntity(validator.validate()).let(liveData::postValue)
    }
}