package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.core.MapperFrom
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
    protected val loadPhotosUseCase: LoadPhotosUseCase
) : BaseViewModel(application), MapperFrom<PhotosState, PhotosUiState> {

    private val _errorMutableLiveData = MutableLiveData<Unit>()
    val errorLiveData: LiveData<Unit>
        get() = _errorMutableLiveData

    private val _photosMutableLiveData = MutableLiveData<List<PictureInfoUiModel>>()
    val photosLiveData: LiveData<List<PictureInfoUiModel>>
        get() = _photosMutableLiveData

    private val _notifyFailMutableLiveData = MutableLiveData<UiFailModel>()
    val notifyFailLiveData: LiveData<UiFailModel>
        get() = _notifyFailMutableLiveData

    var page: Int = 1
    protected val limit: Int = 10

    open fun loadPhotos() {
        viewModelScope.launch {
            loadPhotosUseCase.loadPhotos(ShowPicturesInfo(page, limit)).let(::mapTo)
                .let(::setStatus)
        }
    }

    override fun mapTo(inputModel: PhotosState): PhotosUiState =
        when (inputModel) {
            is PhotosState.Success -> PhotosUiState.Success(
                inputModel.photos.map {
                    PictureInfoUiModel().mapTo(it)
                })
            is PhotosState.Fail -> PhotosUiState.Error(inputModel.errorContainer)
            is PhotosState.Exception -> PhotosUiState.Exception()
        }

    protected fun setStatus(photosState: PhotosUiState) =
        when (photosState) {
            is PhotosUiState.Success -> _photosMutableLiveData.value = photosState.paginationDataList
            is PhotosUiState.Exception -> _errorMutableLiveData.notifyObserver()
            is PhotosUiState.Error -> _notifyFailMutableLiveData.value = photosState.errorContainer
        }
}