package com.example.galleryapp.ui.models.exceptions.photos

import android.content.res.Resources
import com.example.data.core.photos.PhotosServerFail
import com.example.domain.core.HandleFactory
import com.example.domain.core.UiFailModel

class PhotosFailMapUiFactory(private val resources: Resources) : HandleFactory<PhotosServerFail, UiFailModel> {
    override fun handle(e: PhotosServerFail): UiFailModel =
        when(e) {
            is PhotosServerFail.NoData -> UiNoPhotosFailModel(resources)
            else -> UiExceptionFailModel(resources)
        }
}