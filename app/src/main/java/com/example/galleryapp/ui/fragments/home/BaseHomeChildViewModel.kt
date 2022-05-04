package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoDomainEntity
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.galleryapp.ui.fragments.BaseViewModel
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.pagination.PageSource
import com.example.galleryapp.ui.pagination.PhotosPagination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseHomeChildViewModel(
    application: Application,
    private val loadPhotosUseCase: LoadPhotosUseCase, //DoNamed
    private val photosPagination: PhotosPagination
) : BaseViewModel(application) {

    private val page: Int = 1
    private val limit: Int = 10

    val photosFlow = Pager(PagingConfig(pageSize = limit, enablePlaceholders = true)) {
        photosPagination
    }.flow.cachedIn(viewModelScope)

    private val _errorMutableLiveData = MutableLiveData<Unit>()
    val errorLiveData: LiveData<Unit>
        get() = _errorMutableLiveData

    private val _photosMutableLiveData = MutableLiveData<List<PictureInfoDomainEntity>>()
    val photosLiveData: LiveData<List<PictureInfoDomainEntity>>
        get() = _photosMutableLiveData

    private val _notifyFailMutableLiveData = MutableLiveData<UiFailModel>()
    val notifyFailLiveData: LiveData<UiFailModel>
        get() = _notifyFailMutableLiveData

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