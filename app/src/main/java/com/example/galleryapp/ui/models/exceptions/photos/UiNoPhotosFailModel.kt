package com.example.galleryapp.ui.models.exceptions.photos

import android.content.res.Resources
import com.example.galleryapp.R
import com.example.galleryapp.ui.models.BaseUiFailModel

class UiNoPhotosFailModel(override val resources: Resources) : BaseUiFailModel {
    override fun getMessageResId(): Int = R.string.notify_exception_no_pagination_photos
}