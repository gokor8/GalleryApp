package com.example.galleryapp.validators

import com.example.galleryapp.R

interface Validator {

    val errorMessage: String

    fun validate(): String
}
