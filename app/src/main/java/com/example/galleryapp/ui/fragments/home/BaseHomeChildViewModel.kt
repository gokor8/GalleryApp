package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoModel
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.fragments.HomeViewModel
import kotlinx.coroutines.launch

abstract class BaseHomeChildViewModel(
    application: Application,
    private val loadPhotosUseCase: LoadPhotosUseCase
) : BaseViewModel(application) {
    private val _errorMutableLiveData = MutableLiveData<Unit>()
    val errorLiveData: LiveData<Unit>
        get() = _errorMutableLiveData

    private val _photosMutableLiveData = MutableLiveData<List<PictureInfoModel>>()
    val photosLiveData: LiveData<List<PictureInfoModel>>
        get() = _photosMutableLiveData

    private val _notifyFailMutableLiveData = MutableLiveData<UiFailModel>()
    val notifyFailLiveData: LiveData<UiFailModel>
        get() = _notifyFailMutableLiveData

    private val page: Int = 1
    private val limit: Int = 10

    fun loadPhotos() {
        viewModelScope.launch {
            loadPhotosUseCase.loadPhotos(ShowPicturesInfo(page, limit)).let(::setStatus)
        }
    }

    private fun setStatus(photosState: PhotosState) =
        when (photosState) {
            is PhotosState.Success -> _photosMutableLiveData.value = photosState.photos
            is PhotosState.Exception -> _errorMutableLiveData.notifyObserver()
            is PhotosState.Fail -> _notifyFailMutableLiveData.value = photosState.errorContainer
        }
}