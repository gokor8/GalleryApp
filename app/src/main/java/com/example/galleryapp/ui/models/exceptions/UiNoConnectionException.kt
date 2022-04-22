package com.example.galleryapp.ui.models.exceptions

import android.content.res.Resources
import com.example.galleryapp.R
import com.example.galleryapp.ui.models.BaseUiException

class UiNoConnectionException(override val resources: Resources) : BaseUiException {
    override fun getMessageResId(): Int = R.string.notify_exception_no_connection
}