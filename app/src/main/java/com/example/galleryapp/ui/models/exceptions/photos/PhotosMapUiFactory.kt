package com.example.galleryapp.ui.models.exceptions.photos

import android.content.res.Resources
import com.example.domain.entities.photos.PhotosFailds
import com.example.domain.core.HandleFactory
import com.example.domain.core.UiFailModel

class PhotosMapUiFactory(private val resources: Resources) : HandleFactory<PhotosFailds> {
    override fun handle(e: PhotosFailds): UiFailModel =
        when(e) {
            PhotosFailds.NoData -> UiNoPhotosFailModel(resources)
            else -> UiDontKnowFailModel(resources)
        }
}