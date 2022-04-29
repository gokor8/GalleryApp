package com.example.data.datasource.pictures

import com.example.data.api.ImageService
import com.example.domain.core.ErrorContainer
import com.example.domain.core.HandleFactory
import com.example.domain.core.LoadPhotoTypes
import com.example.domain.entities.photos.LoadPhotosModel
import com.example.domain.entities.photos.PhotosFailds
import com.example.domain.entities.states.PhotosState

class PicturesDataSource(
    private val imageService: ImageService,
    private val mapUiFactory: HandleFactory<PhotosFailds>
) {
    private val countPages: Int? = null

    suspend fun getPictures(loadPhotosModel: LoadPhotosModel): PhotosState {
        if (countPages != null && loadPhotosModel.page == countPages) {
            val uiFailModel = mapUiFactory.handle(PhotosFailds.NoData)

            return PhotosState.Fail(uiFailModel)
        }

        val picturesInfoArray =
            imageService.getImagePage(loadPhotosModel.mapTo()).picturesInfoResponse.map { it.mapTo() }

        return PhotosState.Success(picturesInfoArray)
    }
}