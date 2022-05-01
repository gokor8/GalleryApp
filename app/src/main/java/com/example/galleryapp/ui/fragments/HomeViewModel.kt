package com.example.galleryapp.ui.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoModel
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import kotlinx.coroutines.launch

abstract class HomeViewModel(application: Application) : BaseViewModel(application)