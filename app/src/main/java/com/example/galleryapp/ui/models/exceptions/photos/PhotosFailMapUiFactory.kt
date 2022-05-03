package com.example.galleryapp.ui.models.exceptions.photos

import android.content.res.Resources
import com.example.data.core.photos.PhotosServerState
import com.example.domain.core.HandleFactory
import com.example.domain.core.UiFailModel

class PhotosFailMapUiFactory(private val resources: Resources) : HandleFactory<PhotosServerState, UiFailModel> {
    override fun handle(e: PhotosServerState): UiFailModel =
        when(e) {
            is PhotosServerState.NoData -> UiNoPhotosFailModel(resources)
            else -> UiExceptionFailModel(resources)
        }
}