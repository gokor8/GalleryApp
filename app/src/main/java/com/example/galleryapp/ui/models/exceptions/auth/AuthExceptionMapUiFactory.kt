package com.example.galleryapp.ui.models.exceptions.auth

import android.content.res.Resources
import com.example.data.storages.exceptions.CustomEmptyCacheData
import com.example.data.storages.exceptions.CustomNoConnectionException
import com.example.domain.core.HandleFactory
import java.lang.Exception

class AuthExceptionMapUiFactory(private val resources: Resources) : HandleFactory<Exception>{
    override fun handle(e: Exception) =
        when (e) {
            is CustomNoConnectionException -> UiNoConnectionFailModel(resources)
            is CustomEmptyCacheData -> UiCacheFailModel(resources)
            else -> throw e
        }
}