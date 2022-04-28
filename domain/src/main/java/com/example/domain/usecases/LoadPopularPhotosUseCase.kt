package com.example.domain.usecases

import com.example.domain.core.LoadPhotoTypes

class LoadPopularPhotosUseCase : LoadPhotosUseCase {

    override val loadPhotosType: LoadPhotoTypes = LoadPhotoTypes.Popular

    override fun loadPhotos() {

    }
}