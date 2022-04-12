package com.example.galleryapp.fragments

import androidx.lifecycle.ViewModel
import com.example.domain.core.ValidationTypes

interface ValidationViewModel {

    fun validate(str: String, validationType: ValidationTypes)
}