package com.example.galleryapp.ui.models.exceptions

import android.content.res.Resources
import com.example.data.storages.exceptions.CustomEmptyCacheData
import com.example.data.storages.exceptions.CustomNoConnectionException
import com.example.domain.core.ExceptionFactory
import java.lang.Exception

class DataToUiExceptionFactory(private val resources: Resources) : ExceptionFactory {
    override fun handle(e: Exception) =
        when (e) {
            is CustomNoConnectionException -> UiNoConnectionException(resources)
            is CustomEmptyCacheData -> UiCacheException(resources)
            else -> throw e
        }
}