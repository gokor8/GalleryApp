package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.ui.models.states.PhotosUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularFragmentViewModel @Inject constructor(
    application: Application,
    loadNewPhotosUseCase: LoadPopularPhotosUseCase,
    mapperToUiModel: HandleFactory<PhotosState, PhotosUiState>
) : BaseHomeChildViewModel(application, loadNewPhotosUseCase, mapperToUiModel)