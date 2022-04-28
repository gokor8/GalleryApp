package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.fragments.SimpleErrorViewModel
import com.example.galleryapp.ui.models.ErrorHomeChildrenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewFragmentViewModel @Inject constructor(application: Application) :
    BaseViewModel(application), SimpleErrorViewModel {
    private val _errorMutableLiveData = MutableLiveData<ErrorHomeChildrenModel>()
    override val errorLiveData: LiveData<ErrorHomeChildrenModel> = _errorMutableLiveData

    private val photos = mutableListOf<Int>()

    private val page: Int = 1
    private val limit: Int = 10

    fun loadPhotos() {

    }
}