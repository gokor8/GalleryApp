package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.R
import com.example.galleryapp.validators.MultiValidator

class StringsMultiDataValidator(
    override val listValidateStr: List<String>,
    override val errorMessage: String
) : MultiValidator {

    override fun validate(): String {
        var previous: String? = null
        for (str in listValidateStr) {
            if (previous == null)
                previous = str
            else if (previous != str)
                return errorMessage
        }

        return ""
    }
}