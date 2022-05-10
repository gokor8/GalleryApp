package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PhotosUiState
import kotlinx.coroutines.launch

abstract class BaseHomeChildViewModel(
    application: Application,
    protected val loadPhotosUseCase: LoadPhotosUseCase,
    protected val mapperToUiModel: HandleFactory<PhotosState, PhotosUiState>
) : BaseViewModel(application) {

    protected val _errorMutableLiveData = MutableLiveData<Unit>()
    val errorLiveData: LiveData<Unit>
        get() = _errorMutableLiveData

    protected val _photosMutableLiveData = MutableLiveData<List<PictureInfoUiModel>>()
    val photosLiveData: LiveData<List<PictureInfoUiModel>>
        get() = _photosMutableLiveData

    protected val _notifyFailMutableLiveData = MutableLiveData<UiFailModel>()
    val notifyFailLiveData: LiveData<UiFailModel>
        get() = _notifyFailMutableLiveData

    var page: Int = 1
    protected val limit: Int = 10

    open fun loadPhotos() {
        viewModelScope.launch {
            loadPhotosUseCase.loadPhotos(ShowPicturesInfo(page, limit))
                .let(mapperToUiModel::handle)
                .let(::setStatus)
        }
    }

    protected fun setStatus(photosState: PhotosUiState) =
        when (photosState) {
            is PhotosUiState.Success -> _photosMutableLiveData.value =
                photosState.paginationDataList
            is PhotosUiState.Exception -> _errorMutableLiveData.notifyObserver()
            is PhotosUiState.Error -> _notifyFailMutableLiveData.value = photosState.errorContainer
        }
}