package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.domain.usecases.LoadNewPhotosUseCase
import com.example.galleryapp.ui.pagination.PhotosPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewFragmentViewModel @Inject constructor(
    application: Application,
    loadNewPhotosUseCase: LoadNewPhotosUseCase,
    private val photosPagination: PhotosPagination
) : BaseHomeChildViewModel(application, loadNewPhotosUseCase, photosPagination)