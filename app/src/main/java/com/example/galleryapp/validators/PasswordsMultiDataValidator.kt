package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.R
import com.example.galleryapp.validators.MultiValidator
import com.example.galleryapp.validators.Validators

class PasswordsMultiDataValidator(override val listValidateStr: List<String>) : Validators(), MultiValidator {
    override val isNullData: Boolean
        get() = listValidateStr.isEmpty()

    override fun validate(): Int {
        var previous: String? = null
        for(str in listValidateStr){
            if(previous == null)
                previous = str
            else if(previous != str)
                return R.string.error_passwords
        }

        return R.string.empty_error
    }
}