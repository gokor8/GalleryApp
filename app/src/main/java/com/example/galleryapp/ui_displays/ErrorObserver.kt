package com.example.galleryapp.ui_displays

import androidx.lifecycle.LiveData
import com.example.galleryapp.validators.Validator
import com.example.galleryapp.validators.entities.ErrorEntity

class ErrorObserver {

    private val errorMap = mutableListOf<Pair<LiveData<ErrorEntity>, ErrorEntity>>()

    fun addOrRemove(lv: LiveData<ErrorEntity>, errorEntity: ErrorEntity): ErrorEntity {
        if (!errorEntity.hasError) {
            errorMap.remove(Pair(lv, errorEntity))
            return buildError(lv)
        }

        //if(!errorMap.filter { it })
        errorMap.add(Pair(lv, errorEntity))

        return buildError(lv)
    }

    private fun buildError(validator: LiveData<ErrorEntity>): ErrorEntity {
        return errorMap.filter { it.first == validator }.run {
            var errorMessage = ""
            this.forEach {
                errorMessage += "${it.second.errorMessage}\r\n"
            }
            ErrorEntity(errorMessage)
        }
    }
}
