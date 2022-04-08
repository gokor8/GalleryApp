package com.example.galleryapp.validators

import com.example.domain.core.ValidationTypes
import com.example.galleryapp.R
import com.example.galleryapp.ValidationHandler

class DateValidator : Validator {
    override val validateValue: Int? = null
    override val validationType = ValidationTypes.Date

    override fun validate(str: String): Int? {

        val splitArray = str.split('.')
        val dots = str.filter { it == '.' }

        return if(splitArray.size == 3 && dots.length == 2) {
            val cleanStrLength = splitArray.flatMap { it.toList() }.size
            return if(cleanStrLength == 8 && splitArray.last().length == 4)
                null
            else
                R.string.error_date
        }
        else
            R.string.error_date
    }
}