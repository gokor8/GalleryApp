package com.example.domain.usecases

import com.example.domain.core.LoadPhotoTypes
import com.example.domain.repository.MediaRepository

class LoadNewPhotosUseCase(override val mediaRepository: MediaRepository) : LoadPhotosUseCase {

    override val loadPhotosType: LoadPhotoTypes = LoadPhotoTypes.New
}