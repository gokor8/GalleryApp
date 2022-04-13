package com.example.galleryapp.validators.validators_impl

import com.example.galleryapp.R
import com.example.galleryapp.validators.MultiValidator

class PasswordsMultiDataValidator(override val listValidateStr: List<String>) : MultiValidator {

    override val isNullData: Boolean
        get() =
            if (listValidateStr.isNotEmpty()) {
                var hasEmpty = false
                listValidateStr.forEach {
                    if (it.isEmpty()) hasEmpty = true
                }
                hasEmpty
            } else
                true

    override fun validate(): Int {
        var previous: String? = null
        for (str in listValidateStr) {
            if (previous == null)
                previous = str
            else if (previous != str)
                return R.string.error_passwords
        }

        return R.string.empty_error
    }
}