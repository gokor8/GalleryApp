package com.example.galleryapp.validators.entities

import com.example.galleryapp.validators.Validator

data class ErrorEntity(
    private val errorIds: MutableMap<String, Int?>
) {
    val hasError = errorIds.isNotEmpty()

    fun addOrRemove(validator: Validator, errorId: Int?) {
        val validatorName = validator::class.simpleName

        if(errorIds.contains(validatorName) && errorId == null)
            errorIds.remove(validatorName)
        else
            errorIds[validatorName!!] = errorId
    }

    fun getErrorIds() = errorIds

    fun clearErrors() {
        errorIds.clear()
    }
}