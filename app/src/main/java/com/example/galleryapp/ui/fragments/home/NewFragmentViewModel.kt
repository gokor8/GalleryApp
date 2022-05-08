package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.domain.core.HandleFactory
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadNewPhotosUseCase
import com.example.galleryapp.ui.models.states.PhotosUiState
import com.example.galleryapp.ui.pagination.PhotosPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewFragmentViewModel @Inject constructor(
    application: Application,
    loadNewPhotosUseCase: LoadNewPhotosUseCase,
    mapperToUiModel: HandleFactory<PhotosState, PhotosUiState>
) : BaseHomeChildViewModel(application, loadNewPhotosUseCase, mapperToUiModel)