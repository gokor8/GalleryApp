package com.example.galleryapp.validators.multi_data_validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.R

interface MultiDataValidator {

    val validationType: ValidationTypes

    fun validate(vararg strs: String): Int? {
        var previous: String? = null
        for(str in strs){
            if(previous == null)
                previous = str
            else if(previous != str)
                return R.string.error_passwords
        }

        return R.string.empty_error
    }
}