package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PhotosUiState
import com.example.galleryapp.ui.pagination.PhotosPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    application: Application,
    loadPhotosUseCase: LoadPhotosUseCase,
) : BaseHomeChildViewModel(application, loadPhotosUseCase) {

    private var loadJob: Job? = null

    private val searchMutableLiveData = MutableLiveData<List<PictureInfoUiModel>>()
    val searchLiveData: LiveData<List<PictureInfoUiModel>>
        get() = searchMutableLiveData

    fun searchPhotos(id: Int) {
        loadJob?.cancel()

        loadJob = viewModelScope.launch {
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