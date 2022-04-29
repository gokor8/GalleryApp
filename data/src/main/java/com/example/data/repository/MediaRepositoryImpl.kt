package com.example.data.repository

import com.example.data.datasource.pictures.PicturesDataSource
import com.example.domain.core.LoadPhotoTypes
import com.example.domain.entities.photos.LoadPhotosModel
import com.example.domain.entities.states.PhotosState
import com.example.domain.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepositoryImpl(private val picturesDataSource: PicturesDataSource) : MediaRepository {

    override suspend fun loadPictures(
        loadPhotosModel: LoadPhotosModel
    ): PhotosState =
        withContext(Dispatchers.IO) { picturesDataSource.getPictures(loadPhotosModel) }
}