package com.example.galleryapp.ui.mappers

import android.content.res.Resources
import com.example.data.storages.exceptions.CustomEmptyCacheData
import com.example.data.storages.exceptions.CustomNoConnectionException
import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.galleryapp.ui.models.exceptions.auth.UiCacheFailModel
import com.example.galleryapp.ui.models.exceptions.auth.UiNoConnectionFailModel
import java.lang.Exception

class AuthExceptionMapUiFactory(private val resources: Resources) :
    HandleFactory<Exception, UiFailModel> {
    override fun handle(e: Exception) =
        when (e) {
            is CustomNoConnectionException -> UiNoConnectionFailModel(resources)
            is CustomEmptyCacheData -> UiCacheFailModel(resources)
            else -> throw e
        }
}