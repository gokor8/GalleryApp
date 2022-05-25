package com.example.data.datasource.pictures

import com.example.data.api.models.pictures.RequestPictureModel
import com.example.data.core.photos.PhotosServerState

interface PicturesDataSource {
    
    suspend fun getPictures(requestPictureModel: RequestPictureModel): PhotosServerState
}