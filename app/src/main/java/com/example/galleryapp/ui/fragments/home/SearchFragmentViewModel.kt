package com.example.galleryapp.ui.fragments.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.core.ui.Listener
import com.example.galleryapp.ui.mappers.PhotosStateToPhotosUiStateMapper
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PhotosUiState
import com.example.galleryapp.ui.pagination.PhotosPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    application: Application,
    @Named("LoadNewPhotosUseCase") loadPhotosUseCase: LoadPhotosUseCase,
    photosStateToPhotosUiStateMapper: PhotosStateToPhotosUiStateMapper,
    val listener: Listener.Read<String>
) : BaseHomeChildViewModel(application, loadPhotosUseCase, photosStateToPhotosUiStateMapper) {

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
                    loadPhotosUseCase.loadPhotos(ShowPicturesInfo(page, limit))
                        .let(mapperToUiModel::handle)

                if (photosUiState is PhotosUiState.Success) {
                    searchMutableLiveData.value =
                        photosUiState.paginationDataList.filter { it.id == id }
                }
            }
        }
    }
}