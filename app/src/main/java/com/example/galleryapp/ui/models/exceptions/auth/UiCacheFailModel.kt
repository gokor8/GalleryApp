package com.example.galleryapp.ui.models.exceptions.auth

import android.content.res.Resources
import com.example.galleryapp.R
import com.example.galleryapp.ui.models.BaseUiFailModel

class UiCacheFailModel(override val resources: Resources) : BaseUiFailModel {
    override fun getMessageResId(): Int = R.string.notify_exception_chache_keys
}