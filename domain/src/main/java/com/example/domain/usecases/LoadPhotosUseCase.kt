package com.example.domain.usecases

import com.example.domain.core.ServerLoadPhotoTypes
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.repository.MediaRepository

interface LoadPhotosUseCase {
    val serverLoadPhotosType: ServerLoadPhotoTypes
    val mediaRepository: MediaRepository

    suspend fun loadPhotos(showDataInfo: ShowPicturesInfo): PhotosState =
        try {
            serverLoadPhotosType.pair?.let {
                showDataInfo.loadType = it
            }
            mediaRepository.loadPictures(showDataInfo)
        } catch (e: Exception) {
            PhotosState.Exception()
        }
}