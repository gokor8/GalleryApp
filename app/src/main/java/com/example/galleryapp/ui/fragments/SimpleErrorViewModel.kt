package com.example.galleryapp.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.galleryapp.ui.models.ErrorHomeChildrenModel

interface SimpleErrorViewModel {
    val errorLiveData: LiveData<ErrorHomeChildrenModel>
}