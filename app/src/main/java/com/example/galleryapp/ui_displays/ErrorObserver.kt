package com.example.galleryapp.ui_displays

import androidx.lifecycle.LiveData
import com.example.galleryapp.validators.entities.ErrorEntity

class ErrorObserver {

    private val errorsList = mutableListOf<Pair<LiveData<ErrorEntity>, ErrorEntity>>()

    fun addOrRemove(liveData: LiveData<ErrorEntity>, errorEntity: ErrorEntity): ErrorEntity {
        val liveDataError = Pair(liveData, errorEntity)

        if (!errorEntity.hasError) {
            errorsList.remove(liveDataError)
            return buildError(liveData)
        }

        if(!errorsList.contains(liveDataError))
            errorsList.add(liveDataError)

        return buildError(liveData)
    }

    private fun buildError(validator: LiveData<ErrorEntity>): ErrorEntity {
        return errorsList.filter { it.first == validator }.run {
            var errorMessage = ""
            this.forEach {
                errorMessage += "${it.second.errorMessage}\r\n"
            }
            ErrorEntity(errorMessage)
        }
    }
}
