package com.example.domain.usecases

import com.example.domain.core.LoadPhotoTypes
import com.example.domain.entities.photos.LoadPhotosModel
import com.example.domain.entities.states.PhotosState
import com.example.domain.repository.MediaRepository

interface LoadPhotosUseCase {
    val loadPhotosType: LoadPhotoTypes
    val mediaRepository: MediaRepository

    suspend fun loadPhotos(loadPhotosModel: LoadPhotosModel): PhotosState =
        try {
            loadPhotosModel.loadType = loadPhotosType.pair
            mediaRepository.loadPictures(loadPhotosModel)
        } catch (e: Exception) {
            PhotosState.Exception()
        }
}