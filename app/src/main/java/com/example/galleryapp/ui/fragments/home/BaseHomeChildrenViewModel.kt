package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.ui.fragments.HomeViewModel

abstract class BaseHomeChildrenViewModel(
    application: Application,
    loadPopularPhotosUseCase: LoadPopularPhotosUseCase
) : HomeViewModel(application, loadPopularPhotosUseCase)