package com.example.domain.usecases

import com.example.domain.core.LoadPhotoTypes

class LoadNewPhotosUseCase : LoadPhotosUseCase {

    override val loadPhotosType: LoadPhotoTypes = LoadPhotoTypes.New

    override fun loadPhotos() {

    }
}