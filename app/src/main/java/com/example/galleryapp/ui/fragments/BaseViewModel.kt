package com.example.galleryapp.ui.fragments

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val resources: Resources = application.resources

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}