package com.example.domain.repository

import com.example.domain.core.LoadPhotoTypes
import com.example.domain.entities.photos.LoadPhotosModel
import com.example.domain.entities.states.PhotosState

interface MediaRepository {

    suspend fun loadPictures(loadPhotosModel: LoadPhotosModel) : PhotosState
}