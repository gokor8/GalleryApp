package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PhotosUiState
import com.example.galleryapp.ui.pagination.PhotosPagination
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    application: Application,
    loadPhotosUseCase: LoadPhotosUseCase,
    photosPagination: PhotosPagination
) : BaseHomeChildViewModel(application, loadPhotosUseCase, photosPagination) {

    private val searchMutableLiveData = MutableLiveData<List<PictureInfoUiModel>>()
    val searchLiveData: LiveData<List<PictureInfoUiModel>>
        get() = searchMutableLiveData

    fun searchPhotos(id: Int) {
        viewModelScope.launch {
            var photosUiState: PhotosUiState? = null
            while (photosUiState == null || photosUiState is PhotosUiState.Success) {
                photosUiState =
                    loadPhotosUseCase.loadPhotos(ShowPicturesInfo(page, limit)).let(::mapTo)

                if (photosUiState is PhotosUiState.Success) {
                    searchMutableLiveData.value = photosUiState.paginationDataList.filter { it.id == id }
                }
            }
        }
    }
}