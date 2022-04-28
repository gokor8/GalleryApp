package com.example.domain.usecases

import com.example.domain.core.LoadPhotoTypes

interface LoadPhotosUseCase {
    val loadPhotosType: LoadPhotoTypes

    fun loadPhotos()
}