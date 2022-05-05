package com.example.galleryapp.ui.fragments

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.core.MapperFrom
import com.example.domain.entities.states.DomainState
import com.example.galleryapp.ui.models.states.UiState

abstract class BaseViewModel/*<I : DomainState, R : UiState>*/(application: Application) : AndroidViewModel(application)/*, MapperFrom<I,R> */{

    protected val resources: Resources = application.resources

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}