package com.example.galleryapp.ui.models

import android.content.res.Resources
import com.example.domain.core.UiFailModel

interface BaseUiFailModel : UiFailModel {

    val resources: Resources

    override fun getMessage(): String = resources.getString(getMessageResId())
}