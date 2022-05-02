package com.example.galleryapp.di

import com.example.data.core.photos.PhotosServerFail
import com.example.domain.core.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.galleryapp.ui.models.exceptions.auth.AuthExceptionMapUiFactory
import com.example.galleryapp.ui.models.exceptions.photos.PhotosFailMapUiFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsMapUiFactoryModule {

    @Binds
    abstract fun bindAuthToMapUiFactory(authExceptionToUiExceptionFactory: AuthExceptionMapUiFactory): HandleFactory<Exception, UiFailModel>

    @Binds
    abstract fun bindPhotosToMapUiFactory(failMapUiFactory: PhotosFailMapUiFactory): HandleFactory<PhotosServerFail, UiFailModel>
}