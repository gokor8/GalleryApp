package com.example.galleryapp.validators

import com.example.galleryapp.R

interface Validator {
    val isNullData: Boolean
    fun validate(): Int? = R.string.empty_error
}
