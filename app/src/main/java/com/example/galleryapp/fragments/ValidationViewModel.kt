package com.example.galleryapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.core.ValidationTypes
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.ErrorEntity

interface ValidationViewModel {

    val baseLengthValidation: Int

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
}