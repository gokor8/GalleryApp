package com.example.data.datasource.pictures

import com.example.data.api.services.ImageService
import com.example.data.api.models.pictures.RequestPictureModel
import com.example.domain.core.handle_factories.HandleFactory
import com.example.data.core.photos.PhotosServerState
import com.example.domain.core.UiFailModel

class PicturesDataSource(
    private val imageService: ImageService,
    private val mapUiFactory: HandleFactory<PhotosServerState, UiFailModel>
) {
    private var countPages: Int? = null

    suspend fun getPictures(requestPictureModel: RequestPictureModel): PhotosServerState {
        if (countPages != null && requestPictureModel.page == countPages) {
            return PhotosServerState.NoData(mapUiFactory)
        }

        val picturesInfo =
            imageService.getImagePage(requestPictureModel.mapTo())

        countPages = picturesInfo.countOfPages

        return PhotosServerState.Success(picturesInfo.picturesInfoResponse)
    }
}