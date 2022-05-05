package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.core.MapperFrom
import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoDomainEntity
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PhotosUiState
import com.example.galleryapp.ui.pagination.PageSource
import com.example.galleryapp.ui.pagination.PhotosPagination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseHomeChildViewModel(
    application: Application,
    protected val loadPhotosUseCase: LoadPhotosUseCase, //DoNamed
    private val photosPagination: PhotosPagination
) : BaseViewModel(application), MapperFrom<PhotosState, PhotosUiState> {

    var page: Int = 1
    protected val limit: Int = 10

    @Deprecated("Difficult library")
    val photosFlow = Pager(PagingConfig(pageSize = limit, enablePlaceholders = true)) {
        photosPagination
    }.flow.cachedIn(viewModelScope)

    private val _errorMutableLiveData = MutableLiveData<Unit>()
    val errorLiveData: LiveData<Unit>
        get() = _errorMutableLiveData

    private val _photosMutableLiveData = MutableLiveData<List<PictureInfoUiModel>>()
    val photosLiveData: LiveData<List<PictureInfoUiModel>>
        get() = _photosMutableLiveData

    private val _notifyFailMutableLiveData = MutableLiveData<UiFailModel>()
    val notifyFailLiveData: LiveData<UiFailModel>
        get() = _notifyFailMutableLiveData

    fun loadPhotos() {
        viewModelScope.launch {
            loadPhotosUseCase.loadPhotos(ShowPicturesInfo(page, limit)).let(::mapTo).let(::setStatus)
        }
    }

    override fun mapTo(inputModel: PhotosState): PhotosUiState =
        when (inputModel) {
            is PhotosState.Success -> PhotosUiState.Success(inputModel.photos.map {
                PictureInfoUiModel().mapTo(it)
            })
            is PhotosState.Fail -> PhotosUiState.Error(inputModel.errorContainer)
            is PhotosState.Exception -> PhotosUiState.Exception()
        }

    private fun setStatus(photosState: PhotosUiState) =
        when (photosState) {
            is PhotosUiState.Success -> _photosMutableLiveData.value = photosState.paginationDataList
            is PhotosUiState.Exception -> _errorMutableLiveData.notifyObserver()
            is PhotosUiState.Error -> _notifyFailMutableLiveData.value = photosState.errorContainer
        }
}