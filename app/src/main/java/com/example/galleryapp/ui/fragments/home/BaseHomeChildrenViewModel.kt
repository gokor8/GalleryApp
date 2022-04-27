package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.models.ErrorHomeChildrenModel

abstract class BaseHomeChildrenViewModel(application: Application) : BaseViewModel(application) {
    private val _errorMutableLiveData = MutableLiveData<ErrorHomeChildrenModel>()
    val errorLiveData: LiveData<ErrorHomeChildrenModel> = _errorMutableLiveData


}