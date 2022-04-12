package com.example.galleryapp.validators

sealed class Validators {
    abstract val isNullData: Boolean
    abstract fun validate(): Int?
}
