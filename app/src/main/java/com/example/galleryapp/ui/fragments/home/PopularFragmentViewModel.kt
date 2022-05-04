package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.ui.pagination.PhotosPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularFragmentViewModel @Inject constructor(
    application: Application,
    loadNewPhotosUseCase: LoadPopularPhotosUseCase,
    private val photosPagination: PhotosPagination
) : BaseHomeChildViewModel(application, loadNewPhotosUseCase, photosPagination)