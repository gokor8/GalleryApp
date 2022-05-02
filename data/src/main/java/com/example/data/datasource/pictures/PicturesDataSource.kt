package com.example.data.datasource.pictures

import com.example.data.api.ImageService
import com.example.data.api.models.pictures.RequestPictureModel
import com.example.domain.core.HandleFactory
import com.example.data.core.photos.PhotosServerFail
import com.example.data.core.photos.PhotosServerSuccess
import com.example.domain.core.MapperTo
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.PhotosState

class PicturesDataSource(
    private val imageService: ImageService,
    private val mapUiFactory: HandleFactory<PhotosServerFail, UiFailModel>
) {
    private val countPages: Int? = null

    suspend fun getPictures(requestPictureModel: RequestPictureModel): MapperTo<PhotosState> {
        if (countPages != null && requestPictureModel.page == countPages) {
            return PhotosServerFail.NoData(mapUiFactory)
        }

        val picturesInfoArray =
            imageService.getImagePage(requestPictureModel.mapTo()).picturesInfoResponse

        return PhotosServerSuccess.Success(picturesInfoArray)
    }
}