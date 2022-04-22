package com.example.galleryapp.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.validators.entities.ErrorUiModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    protected fun<E : ErrorUiModel> checkingErrors(listLiveDates: List<LiveData<E>>): Boolean {
        var withoutErrors = true

        listLiveDates.forEach {
            if (it.value == null) {
                withoutErrors = false
            } else if (it.value!!.hasError) {
                withoutErrors = false
            }
        }

        return withoutErrors
    }
}