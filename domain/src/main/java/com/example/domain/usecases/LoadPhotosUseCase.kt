package com.example.domain.usecases

import com.example.domain.core.LoadPhotoTypes
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.repository.MediaRepository

interface LoadPhotosUseCase {
    val loadPhotosType: LoadPhotoTypes
    val mediaRepository: MediaRepository

    suspend fun loadPhotos(showDataInfo: ShowPicturesInfo): PhotosState =
        try {
            showDataInfo.loadType = loadPhotosType.pair
            mediaRepository.loadPictures(showDataInfo)
        } catch (e: Exception) {
            PhotosState.Exception()
        }
}