package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.domain.usecases.LoadNewPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewFragmentViewModel @Inject constructor(
    application: Application,
    loadNewPhotosUseCase: LoadNewPhotosUseCase
) : BaseHomeChildViewModel(application, loadNewPhotosUseCase)