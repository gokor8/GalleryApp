package com.example.data.repository

import com.example.data.api.models.pictures.RequestPictureModel
import com.example.data.datasource.pictures.PicturesDataSource
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.repository.MediaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepositoryImpl(
    private val picturesDataSource: PicturesDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MediaRepository {

    override suspend fun loadPictures(
        showDataInfo: ShowPicturesInfo
    ): PhotosState =
        withContext(dispatcher) {
            val requestPictureModel = RequestPictureModel().mapTo(showDataInfo)
            picturesDataSource.getPictures(requestPictureModel).mapTo()
        }
}